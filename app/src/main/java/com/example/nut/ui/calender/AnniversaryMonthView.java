package com.example.nut.ui.calender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

class AnniversaryMonthView extends MonthView {
    Paint mPaint = new Paint();

    Float mRadius = 3.0f;

    public AnniversaryMonthView(Context context) {
        super(context);
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
}