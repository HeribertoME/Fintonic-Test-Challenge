package com.hmelizarraraz.fintonictest.data.models.enums

import kotlin.jvm.internal.Intrinsics




enum class MonthEnum(private val monthName: String) {

    JANUARY("Enero"),
    FEBRUARY("Febrero"),
    MARCH("Marzo"),
    APRIL("Abril"),
    MAY("Mayo"),
    JUNE("Junio"),
    JULY("Julio"),
    AUGUST("Agosto"),
    SEPTEMBER("Septiembre"),
    OCTOBER("Octubre"),
    NOVEMBER("Noviembre"),
    DECEMBER("Diciembre");

    private fun getMonth(): String = this.monthName

    companion object {

        fun getMonthByIndex(index: Int): String = values()[index].getMonth()

        fun getIndexByMonth(month: String): Int {
            for (i in 0 until values().size) {
                if (values()[i].getMonth().equals(month, ignoreCase = true)) {
                    return i
                }
            }
            return 0
        }
    }
}