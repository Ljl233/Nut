package com.example.nut.ui.calender

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.nut.R
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView

class CalendarFragment : Fragment() {

    lateinit var mCalendarView: CalendarView
    lateinit var mCurDay: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_calender, container, false)

        val toolbar: Toolbar = root.findViewById(R.id.calendar_toolbar)
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

        mCalendarView = root.findViewById(R.id.calendar)
        mCurDay = root.findViewById(R.id.calendar_cur_day)
        mCurDay.text = mCalendarView.curDay.toString()
        mCurDay.setOnClickListener { v ->
            mCalendarView.scrollToCurrent()
        }

        root.findViewById<TextView>(R.id.calendar_year_month).text = "${mCalendarView.curYear}.${mCalendarView.curMonth}"

        initData()
        return root
    }

    private fun initData() {
        val year = mCalendarView.curYear
        val month = mCalendarView.curMonth

        val map = HashMap<String, Calendar>()
        map.put(getSchemeCalendar(year, month, 15, 0x40db25, "化学模拟").toString(),
                getSchemeCalendar(year, month, 15, 0x40db25, "化学模拟"))
        map.put(getSchemeCalendar(year, month, 18, 0x3700B3, "小周生日").toString(),
                getSchemeCalendar(year, month, 18, 0x3700B3, "小周生日"))
        map.put(getSchemeCalendar(year, month, 22, 0x895439, "英语PRE").toString(),
                getSchemeCalendar(year, month, 22, 0x895439, "英语PRE"))

        mCalendarView.setSchemeDate(map)
    }

    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = color //如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        return calendar
    }
}