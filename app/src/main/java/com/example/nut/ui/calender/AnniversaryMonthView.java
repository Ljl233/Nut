package com.example.nut.ui.calender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

public class AnniversaryMonthView extends MonthView {
/*
    Paint mPaint = new Paint();

    Float mRadius = 3.0f;

    public AnniversaryMonthView(Context context) {
        super(context);
        Log.e("monthview", "this");
    }


    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        mPaint.setColor(0xC2BCB9);
        canvas.drawCircle((float) (x + mItemWidth / 2.0), (float) (y + mItemHeight / 2.0), (float) (mItemHeight / 2.0), mPaint);
        return true;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        mPaint.setColor(calendar.getSchemeColor());
        canvas.drawCircle((x + mItemWidth / 2.0f - mRadius / 2.0f), (y + mItemHeight - mRadius / 2.0f), mRadius, mPaint);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        mPaint.setColor(calendar.getSchemeColor());
        canvas.drawText(calendar.getScheme(), (x + mItemWidth / 2.0f), y + mTextBaseLine, mPaint);
    }

 */

    private Paint mSchemeBasicPaint = new Paint();
    private int mPadding;
    private int mH, mW;
    Float mRadius = 15.0f;

    public AnniversaryMonthView(Context context) {
        super(context);

        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setColor(0xff333333);
        mSchemeBasicPaint.setFakeBoldText(true);
        mPadding = dipToPx(getContext(), 4);
        mH = dipToPx(getContext(), 2);
        mW = dipToPx(getContext(), 8);
    }


    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        mSelectedPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x + mItemWidth / 2, y + mItemHeight / 2, mItemWidth / 2, mSelectedPaint);
        return true;
    }

    /**
     * onDrawSelected
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     * @param y        日历Card y起点坐标
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        Integer integer = calendar.getSchemeColor();
        integer += 0xff000000;
        mSchemeBasicPaint.setColor(integer);
        Log.e("color", Integer.toHexString(integer));
        canvas.drawCircle((x + mItemWidth / 2.0f), y + mItemHeight / 2, mRadius, mSchemeBasicPaint);
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        int cx = x + mItemWidth / 2;
        int top = y - mItemHeight / 6;
        if (hasScheme) {

            canvas.drawText(calendar.getScheme(), cx, mTextBaseLine + y + mItemHeight / 10, mSchemeTextPaint);

            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);
//
//            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight / 10,
//                    calendar.isCurrentDay() ? mCurDayLunarTextPaint :
//                            mCurMonthLunarTextPaint);

        } else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                            calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}