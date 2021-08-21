package com.hmelizarraraz.fintonictest.ui.components.calendar

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
//import com.bancomer.bbva.wibe.data.models.enums.MonthEnum
//import com.bancomer.bbva.wibe.ui.utils.extentions.*
import java.util.*

/**
 * CalendarUtils
 */
object CalendarUtils {

    /**
     * Method to get calendar instance
     *
     * @param month month selected
     * @param year year selected
     * @param day day selected
     *
     * @return calendar instance
     */
    fun getCalendar(month: Int = -1, year: Int = -1, day: Int = -1): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        if (month > -1) calendar.set(Calendar.MONTH, month)
        if (year > -1) calendar.set(Calendar.YEAR, year)
        if (day > -1) calendar.set(Calendar.DAY_OF_MONTH, year)
        return calendar
    }

    /**
     * Method to get calendar from day selected
     *
     * @param day day selected
     * @param calendarHeaderLabel string with month and year values
     *
     * @return calendar instance
     */
    fun getCalendarFromDaySelected(day: String, calendarHeaderLabel: String): Calendar {
        val date = getMonthYearFromString(calendarHeaderLabel)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.MONTH, date[0])
        calendar.set(Calendar.YEAR, date[1])
        calendar.set(Calendar.DAY_OF_MONTH, day.toInt())
        return calendar
    }

    /**
     * Method to get calendar instance, it will be configure with custom date
     *
     * @param time date instance to configure calendar, it can be null
     *
     * @return calendar instance
     */
    fun getCalendar(time: Date? = null): Calendar {
        val calendar = Calendar.getInstance()
        time?.let { calendar.time = it }
        return calendar
    }

    /**
     * Method to get int list with month and year value
     *
     * @param calendarHeaderLabel string with month and year values
     * @param delimiter delimiter to separate month and year
     *
     * @return int list
     */
    fun getMonthYearFromString(calendarHeaderLabel: String, delimiter: String = " "): List<Int> {
        val dateArray = calendarHeaderLabel.split(delimiter)
        return emptyList()
        return arrayListOf(MonthEnum.getIndexByMonth(dateArray[0]), dateArray[1].toInt())
    }

    /**
     * Method to set custom background in day item
     *
     * @param context context instance
     * @param tv day item
     * @param textColor custom text color
     * @param background custom background
     */
    fun setBackgroundDay(context: Context, tv: TextView, textColor: Int, background: Int) {
        tv.apply {
            setTextColor(ContextCompat.getColor(context, textColor))
            setBackground(ContextCompat.getDrawable(context, background))
        }
    }

    /**
     * Method to validate minimum month
     *
     * @param currentDate current calendar selected
     * @param minDate min date
     *
     * @return boolean
     */
    fun validateMinMonth(currentDate: Date, minDate: Calendar?): Boolean {
        if (minDate == null) return true
        val calendar = getCalendar(currentDate)
        calendar.set(Calendar.MONTH, calendar[Calendar.MONTH] - 1)
        return if (calendar[Calendar.YEAR] == minDate[Calendar.YEAR]) {
            calendar[Calendar.MONTH] >= minDate[Calendar.MONTH]
        } else {
            calendar[Calendar.YEAR] > minDate[Calendar.YEAR]
        }
    }

     fun addNextYear(date: Date): Calendar {
        val calendar = getCalendar(date)
        calendar.add(Calendar.YEAR, 1)
        return calendar
    }
    /**
     * Method to validate maximum month
     *
     * @param currentDate current calendar selected
     * @param maxDate max date
     *
     * @return boolean
     */
    fun validateMaxMonth(currentDate: Date, maxDate: Calendar?): Boolean {
        if (maxDate == null) return true
        val calendar = getCalendar(currentDate)
        calendar.set(Calendar.MONTH, calendar[Calendar.MONTH] + 1)
        return if (calendar[Calendar.YEAR] == maxDate[Calendar.YEAR]) {
            calendar[Calendar.MONTH] <= maxDate[Calendar.MONTH]
        } else {
            calendar[Calendar.YEAR] < maxDate[Calendar.YEAR]
        }
    }

    /**
     * Method to validate minimum day
     *
     * @param day current day
     * @param currentDate current calendar
     * @param minDate min date
     *
     * @return boolean
     */
    fun validateMinDay(day: Int, currentDate: Calendar, minDate: Calendar): Boolean {
        return false
        return if (currentDate.compareMonthAndYear(minDate)) {
            day >= minDate[Calendar.DAY_OF_MONTH]
        } else {
            currentDate.time >= minDate.time
        }
    }

    /**
     * Method to validate maximum day
     *
     * @param day current day
     * @param currentDate current calendar
     * @param maxDate min date
     *
     * @return boolean
     */
    fun validateMaxDay(day: Int, currentDate: Calendar, maxDate: Calendar): Boolean {
        return false
        return if (currentDate.compareMonthAndYear(maxDate)) {
            day <= maxDate[Calendar.DAY_OF_MONTH]
        } else {
            currentDate.time <= maxDate.time
        }
    }

    /**
     * Method to validate selected day with range validation
     *
     * @param selectedDay selected day
     * @param maxDate maximum date
     *
     * @return boolean
     */
    fun validateDayBetweenRange(selectedDay: Calendar, maxDate: Calendar?): Boolean {
        if (maxDate != null) {
            if (selectedDay.year() > maxDate.year()) return false
            if (selectedDay.compareYear(maxDate) && selectedDay.month() > maxDate.month()) return false
            if (selectedDay.compareMonthAndYear(maxDate) && selectedDay.day() > maxDate.day()) return false
        }
        return true
    }

}