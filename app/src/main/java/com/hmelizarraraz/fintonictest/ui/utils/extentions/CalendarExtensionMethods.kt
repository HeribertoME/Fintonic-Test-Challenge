package com.hmelizarraraz.fintonictest.ui.utils.extentions

import java.util.*

fun Calendar.compareDay(calendarToCompare: Calendar) =
    calendarToCompare.get(Calendar.DATE) == get(Calendar.DATE)

fun Calendar.compareMonth(calendarToCompare: Calendar) =
    calendarToCompare.get(Calendar.MONTH) == get(Calendar.MONTH)


fun Calendar.compareMonthAndYear(calendarToCompare: Calendar) =
    compareMonth(calendarToCompare) && compareYear(calendarToCompare)

fun Calendar.compareYear(calendarToCompare: Calendar) =
    calendarToCompare.get(Calendar.YEAR) == this.get(Calendar.YEAR)

fun Calendar.day() = this.get(Calendar.DATE)

fun Calendar.month() = this.get(Calendar.MONTH)

fun Calendar.year() = this.get(Calendar.YEAR)



