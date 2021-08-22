package com.hmelizarraraz.fintonictest.ui.components.calendar

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
//import com.bancomer.bbva.wibe.data.models.enums.MonthEnum
//import com.bancomer.bbva.wibe.ui.utils.extentions.*
import com.hmelizarraraz.fintonictest.R
import com.hmelizarraraz.fintonictest.data.models.enums.MonthEnum
import com.hmelizarraraz.fintonictest.databinding.ComponentWibeCalendarBinding
import com.hmelizarraraz.fintonictest.ui.utils.Constants
import com.hmelizarraraz.fintonictest.ui.utils.extentions.*
import java.util.*


/**
 * WibeCalendarComponent
 *
 * @param context context instance
 * @param attrs param to add new attributes
 * @param defStyleAttr param to change style of edit text
 */
class CalendarComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    /**
     * Component view instance
     */
    private lateinit var mBinding: ComponentWibeCalendarBinding
    /**
     * Day list of month selected
     */
    private val days = arrayListOf<TextView>()
    /**
     * Day selected from calendar
     */
    private var selectedDay: Calendar? = null
    /**
     * Custom min date
     */
    private var minDate: Calendar? = getCalendarDefault()
    /**
     * Custom max date
     */
    private var maxDate: Calendar? = null
    /**
     * Listener to get day of item selected
     */
    private var listener: ((Date) -> Unit)? = null
    /**
     * Counter for calendar days
     */
    private var counter = 1
    /**
     * Display metrics instance
     */
    private val displayMetrics = DisplayMetrics()

    init {
        mBinding = ComponentWibeCalendarBinding.inflate(LayoutInflater.from(context), this, true)
    }

    /**
     * Method to set min date
     *
     * @return this
     */
    fun setMinDate(minDate: Calendar) = apply {
        this.minDate = minDate
    }

    /**
     * Method to set max date
     *
     * @return this
     */
    fun setMaxDate(maxDate: Calendar) = apply {
        this.maxDate = maxDate
    }

    /**
     * Method to set day selected listener
     *
     * @param daySelectedListener listener instance
     */
    fun setDaySelectedListener(daySelectedListener: (Date) -> Unit) {
        this.listener = daySelectedListener
    }

    /**
     * Method to init view
     */
    fun init() {
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        configureCalendar()
        mBinding.ibBackMonth.setOnClickListener { moveCalendar(-1) }
        mBinding.ibNextMonth.setOnClickListener { moveCalendar(1) }
    }

    /**
     * Method to move calendar to previous or next month
     *
     * @param newMonth int value with next month
     */
    private fun moveCalendar(newMonth: Int) {
        val date = CalendarUtils.getMonthYearFromString(mBinding.tvMonth.string())
        configureCalendar(date[0], date[1], newMonth)
    }

    /**
     * Method to configure calendar
     *
     * @param month month selected
     * @param year year selected
     * @param newMonth new month selected
     */
    private fun configureCalendar(month: Int = -1, year: Int = -1, newMonth: Int? = null) {
        val calendar = CalendarUtils.getCalendar(month, year)
        newMonth?.let { calendar.set(Calendar.MONTH, calendar.month() + newMonth) }
        mBinding.tvMonth.text = context.getString(
            R.string.calendar_header,
            MonthEnum.getMonthByIndex(calendar.month()).toUpperCase(), calendar.year().toString()
        )
        clear()
        fillCalendar(calendar)
        mBinding.ibBackMonth.isEnabled = CalendarUtils.validateMinMonth(calendar.time, minDate)
        mBinding.ibNextMonth.isEnabled = CalendarUtils.validateMaxMonth(calendar.time, CalendarUtils.addNextYear(minDate!!.time))
    }

    /**
     * Method to fill calendar
     *
     * @param calendar calendar instance
     */
    private fun fillCalendar(calendar: Calendar) {
        val dayOfWeek = calendar[Calendar.DAY_OF_WEEK]
        val totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        createColumnsAndRows(dayOfWeek, totalDays)
        hasSelectedDay()
    }

    /**
     * Method to set selected days if some day was selected
     */
    private fun hasSelectedDay() {
        if (selectedDay == null) return

        val tempSelectedDay = CalendarUtils.getCalendar(selectedDay!!.time)
        val dateArray = CalendarUtils.getMonthYearFromString(mBinding.tvMonth.string())
        val date = CalendarUtils.getCalendar(dateArray[0], dateArray[1])

        for (i in 0 until 5) {
            val numberToSum = if (i == 0) 0 else 1
            tempSelectedDay.set(Calendar.DAY_OF_MONTH, tempSelectedDay.day() + numberToSum)
            if (date.compareMonthAndYear(tempSelectedDay) && days[tempSelectedDay.day() - 1].isClickable) {
                CalendarUtils.setBackgroundDay(
                    context,
                    days[tempSelectedDay.day() - 1],
                    R.color.white,
                    R.drawable.bg_calendar_fill_day
                )
            }
        }
    }

    /**
     * Method to create colums and rows of calendar
     *
     * @param dayOfWeek day of week where month start
     * @param totalDays total days of month
     */
    private fun createColumnsAndRows(dayOfWeek: Int, totalDays: Int) {
        counter = 1
        for (row in 0..5) {
            val llRow = getRowLayout()
            createDays(row, llRow, dayOfWeek, totalDays)
            mBinding.llDays.addView(llRow)
            if (counter > totalDays) break
        }
    }

    /**
     * Method to create days per week
     *
     * @param row row of calendar
     * @param llRow linear layout of calendar row
     * @param dayOfWeek day of week where month start
     * @param totalDays total days of month
     */
    private fun createDays(row: Int, llRow: LinearLayout, dayOfWeek: Int, totalDays: Int){
        for (day in 1 until 8) {
            //Validate if counter is equals to total days, also it will fill next days of row
            if (counter > totalDays) {
                for (lastEmptyDays in day until 8) llRow.addView(createDayItem(null))
                break
            }
            val tvDay: TextView
            //This "if" will create empty spaces and days of firs row
            if (row == 0) {
                if (day >= dayOfWeek) {
                    tvDay = createDayItem(counter.toString())
                    counter += 1
                } else {
                    tvDay = createDayItem(null)
                }
            } else {
                //This "else" will create days items
                tvDay = createDayItem(counter.toString())
                counter += 1
            }
            //if day item is different to null or empty, it will set click listener
            if (!tvDay.text.isNullOrEmpty()) {
                days.add(tvDay)
            }
            llRow.addView(tvDay)
        }
    }

    /**
     * Method to get row layout of calendar
     *
     * @return linear layout
     */
    private fun getRowLayout(): LinearLayout {
        val llRow = LinearLayout(context)
        llRow.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f)
        llRow.gravity = Gravity.START
        return llRow
    }

    /**
     * Method to select days of calendar, at the end it will save day selected
     *
     * @param calendar current calendar instance
     */
    private fun selectDays(calendar: Calendar) {
        val tvDay: TextView = days[calendar.day() - 1]
        val totalDays: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        //All days background will replaced for border background
        days.forEach { tv ->
            if (tv.isClickable) CalendarUtils.setBackgroundDay(
                context,
                tv,
                R.color.teal,
                R.drawable.bg_calendar_border_day
            )
        }
        //Next five days background will replaced for fill background
        for (day in 0 until 5) {
            val nextDay = (tvDay.string().toInt() - 1) + day
            if (nextDay < totalDays && days[nextDay].isClickable)
                CalendarUtils.setBackgroundDay(context, days[nextDay], R.color.white, R.drawable.bg_calendar_fill_day)
        }
        selectedDay = CalendarUtils.getCalendarFromDaySelected(tvDay.string(), mBinding.tvMonth.text.toString())
    }

    /**
     * Method to create day item
     *
     * @param day day number, if it is null, it will not set day style
     *
     * @return text view
     */
    private fun createDayItem(day: String? = null): TextView {
        val tvDay = TextView(context)
        tvDay.layoutParams = LayoutParams(0, ((displayMetrics.widthPixels * .75) * .14).toInt(), 0.14f)
        tvDay.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        tvDay.gravity = Gravity.CENTER
        day?.let {
            tvDay.text = day
            if (isDayBetweenMinMaxDates(day)) {
                tvDay.isClickable = true
                CalendarUtils.setBackgroundDay(context, tvDay, R.color.teal, R.drawable.bg_calendar_border_day)
                tvDay.setOnClickListener {
                    val calendar = CalendarUtils.getCalendarFromDaySelected(tvDay.string(), mBinding.tvMonth.string())
                    selectDays(calendar)
                    listener?.invoke(calendar.time)
                }
            } else {
                tvDay.isClickable = false
                CalendarUtils.setBackgroundDay(
                    context,
                    tvDay,
                    R.color.light_gray,
                    R.drawable.bg_calendar_gray_border_day
                )
            }
        }
        return tvDay
    }

    /**
     * Method to validate if current day is between min and max date
     *
     * @param day current day
     *
     * @return boolean
     */
    private fun isDayBetweenMinMaxDates(day: String): Boolean {
        if (minDate == null && maxDate == null) return true
        val dateArray = CalendarUtils.getMonthYearFromString(mBinding.tvMonth.string())
        val date = CalendarUtils.getCalendar(dateArray[0], dateArray[1])

        minDate?.let {
            if (CalendarUtils.validateMinDay(day.toInt(), date, minDate!!)) {
                maxDate?.let {
                    return CalendarUtils.validateMaxDay(day.toInt(), date, maxDate!!)
                }
                return true
            }
            return false
        }
        return true
    }

    /**
     * Method to get list of selected dates
     *
     * @return date list
     */
    fun getSelectedDates(): List<Date> {
        val dates = arrayListOf<Date>()
        if (selectedDay == null) return dates
        val tempSelectedDay: Calendar = CalendarUtils.getCalendar(selectedDay!!.time)
        tempSelectedDay.set(Calendar.HOUR, 0)
        tempSelectedDay.set(Calendar.MINUTE, 0)

        for (day in 0 until 5) {
            if (CalendarUtils.validateDayBetweenRange(tempSelectedDay, maxDate)) {
                dates.add(tempSelectedDay.time)
            }
            tempSelectedDay.set(Calendar.DAY_OF_MONTH, tempSelectedDay[Calendar.DAY_OF_MONTH] + 1)
        }
        return dates
    }

    /**
     * Method to clear calendar
     */
    private fun clear() {
        days.clear()
        mBinding.llDays.removeAllViews()
    }

    private fun getCalendarDefault(): Calendar {
        return Calendar.getInstance()
    }

}