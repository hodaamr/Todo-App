package com.route.todoappc40gsat

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer

class CalendarDayWeekContainer(val itemCalendarDayView: View) : ViewContainer(itemCalendarDayView) {
    val dayWeekTextView: TextView = itemCalendarDayView.findViewById(R.id.calendar_day_week)
    val dayMonthTextView: TextView = itemCalendarDayView.findViewById(R.id.calendar_day_month)


}
