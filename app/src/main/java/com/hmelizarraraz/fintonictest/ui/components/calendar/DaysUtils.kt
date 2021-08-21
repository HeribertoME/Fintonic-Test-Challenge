package com.hmelizarraraz.fintonictest.ui.components.calendar

//import com.bancomer.bbva.wibe.ui.utils.extentions.*
import java.util.*

/**
 * DaysUtils
 */
object DaysUtils {

    /**
     * Method to get first date of policy validity
     *
     * @param policyStartDate calendar instance of policy start date
     * @param currentDate current date instance
     *
     * @return calendar instance of policy first date
     */
    fun getStartPeriodDate(policyStartDate: Calendar, currentDate: Calendar = Calendar.getInstance()): Calendar {
        if (policyStartDate.compareMonthAndYear(currentDate)) return policyStartDate

        val firstDate: Calendar = CalendarUtils.getCalendar(currentDate.time)
        if (currentDate.day() < policyStartDate.day() - 1) {
            if (currentDate.day() == currentDate.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                firstDate.set(Calendar.DAY_OF_MONTH, currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1)
            } else {
                getFirstDateLastMonth(firstDate, policyStartDate)
            }
        } else {
            if (policyStartDate.day() <= currentDate.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                firstDate.set(Calendar.DAY_OF_MONTH, policyStartDate.day() - 1)
            } else {
                firstDate.set(Calendar.DAY_OF_MONTH, firstDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1)
            }
        }
        return firstDate
    }

    /**
     * Method to get end date of policy validity
     *
     * @param policyStartDate calendar instance of policy start date
     * @param startPeriod start period date instance
     *
     * @return calendar instance of policy end date
     */
    fun getEndPeriodDate(policyStartDate: Calendar, startPeriod: Calendar): Long {
        val nextDate: Calendar = CalendarUtils.getCalendar(startPeriod.time)
        nextDate.set(Calendar.DAY_OF_MONTH, 1)
        nextDate.set(Calendar.MONTH, nextDate.month() + 1)

        if(nextDate.getActualMaximum(Calendar.DAY_OF_MONTH) < startPeriod.day()){
            nextDate.set(Calendar.DAY_OF_MONTH, nextDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 2)
        } else {
            if(policyStartDate.day() < 3 && policyStartDate.day() != startPeriod.day()){
                nextDate.set(Calendar.DAY_OF_MONTH, nextDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1)
            } else {
                nextDate.set(Calendar.DAY_OF_MONTH, policyStartDate.day() - 2)
            }
        }
        return nextDate.timeInMillis
    }

    /**
     * Method to get first date of last month
     *
     * @param firstDate first date calendar
     * @param policyStartDate calendar instance of policy start date
     */
    private fun getFirstDateLastMonth(firstDate: Calendar, policyStartDate: Calendar) {
        firstDate.set(Calendar.DAY_OF_MONTH, 1)
        firstDate.set(Calendar.MONTH, firstDate.month() - 1)
        if (firstDate.compareMonth(policyStartDate)) {
            firstDate.time = policyStartDate.time
            return
        }

        if ((policyStartDate.day() - 1) <= firstDate.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            firstDate.set(Calendar.DAY_OF_MONTH, policyStartDate.day() - 1)
        } else {
            firstDate.set(Calendar.DAY_OF_MONTH, firstDate.getActualMaximum(Calendar.DAY_OF_MONTH) - 1)
        }
    }

}