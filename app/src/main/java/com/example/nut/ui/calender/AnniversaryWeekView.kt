package com.example.nut.ui.calender

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.WeekView

class AnniversaryWeekView(mContent: Context) : WeekView(mContent) {
    val mPaint = Paint()

    val mRadius: Float = 3.0F


    override fun onDrawText(canvas: Canvas?, calendar: Calendar?, x: Int, hasScheme: Boolean, isSelected: Boolean) {
        mPaint.setColor(calendar?.schemeColor!!)
        Log.e("simpleName",calendar.schemeColor.toString())
        canvas?.drawText(calendar.scheme!!, (x + mItemWidth / 2).toFloat(), y + mTextBaseLine, mPaint)
    }

    override fun onDrawSelected(canvas: Canvas?, calendar: Calendar?, x: Int, hasScheme: Boolean): Boolean {
        mPaint.setColor(0xC2BCB9)
        canvas?.drawCircle(x.toFloat() + mItemWidth / 2, y + mItemHeight / 2, (mItemHeight / 2).toFloat(), mPaint)
        return true
    }

    override fun onDrawScheme(canvas: Canvas?, calendar: Calendar?, x: Int) {
        mPaint.setColor(calendar?.schemeColor!!)
        canvas?.drawCircle(x + mItemWidth / 2 - mRadius / 2, y + mItemHeight - mRadius / 2, mRadius, mPaint)
    }
}