package com.hmelizarraraz.fintonictest.ui.utils.extentions

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan
import uk.co.chrisjenx.calligraphy.TypefaceUtils
import java.lang.Long.parseLong
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension method to get capitalize string, if string is null, function return empty string
 *
 * @return new string
 */
fun String?.capitalizeName(): String {
    if (this == null || this.isEmpty()) {
        return ""
    }
    return this.substring(0, 1).toUpperCase() + this.substring(1).toLowerCase()
}

/**
 * Extension method to get same string without last char, if string is null, function return empty string
 *
 * @return new string
 */
fun String?.removeLast(): String {
    if (this == null) {
        return ""
    }
    return this.substring(0, this.length - 1)
}

/**
 * Extension method to get same string, if string is null, function return empty string
 *
 * @return new string
 */
fun String?.notNull() = this ?: ""

/**
 * Extension method to get string with plate format
 *
 * @return string with plate format
 */
fun String?.plateFormat(): String {
    if (this == null || this.length < 5) {
        return ""
    }
    return this.substring(0, 1) + "-" + this.substring(1, 3) + "-" + this.substring(3, this.length)
}

/**
 * Extension method to convert string number to date object
 *
 * @return date object
 */
fun String?.date(): Date {
    if (this == null) {
        return Date()
    }
    return try {
        val time = parseLong(this)
        val date = Date()
        date.time = time
        date
    } catch (e: NumberFormatException) {
        Date()
    }
}

/**
 * Extension method to get date format string from time string
 *
 * example: from "1570075579" to "03/10/2019"
 *
 * @return date format string
 */
fun String?.timeDateToDateFormatString(): String {
    val date = this.date()
    return SimpleDateFormat("dd/MM/yyyy", Locale("es")).format(date)
}

/**
 * Extension method to get date format string from time string
 *
 * example: from "1570075579" to "03-10-2019"
 *
 * @return date format string
 */
fun String?.timeDateToDateFormatStringWithDash(): String {
    val date = this.date()
    return SimpleDateFormat("dd-MM-yyyy", Locale("es")).format(date)
}

/**
 * Extension method to get time format string from time string
 *
 * example: from "1570075579" to "09:31"
 *
 * @return time format string
 */
fun String?.timeDateToTimeFormatString(): String {
    val date = this.date()
    return SimpleDateFormat("HH:mm", Locale("es")).format(date)
}

/**
 * Extension method to format string to credit card format
 *
 * example: from 424242******4242 to 4242-42**-****-4242
 *
 * @return 4242-42**-****-4242
 */
fun String?.cardNumber(): String {
    if (this == null || this.length < 16) {
        return ""
    }
    val cardNumber = StringBuilder()
    this.split("").forEachIndexed { index, value ->
        cardNumber.append(value)
        if (index % 4 == 0 && index != 0 && index != 16) {
            cardNumber.append("-")
        }
    }
    return cardNumber.toString()
}

/**
 * Extension method to format amount string
 *
 * example: from 1199.43 to $1,199.43
 *
 * @return
 */
fun String?.amountFormat(): String {
    if (this == null) {
        return ""
    }
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    val decimalFormat = numberFormat as DecimalFormat
    decimalFormat.applyPattern("$#,###,###.00")
    return decimalFormat.format(this.toBigDecimal())
}

/**
 * Extension method to get spanned from string
 *
 * @return spanned object
 */
fun String?.spanned(): Spanned {
    val spannedString = if (this == null) "" else this
    val spannedText: Spanned
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        spannedText = Html.fromHtml(spannedString, Html.FROM_HTML_MODE_LEGACY)
    } else {
        spannedText = Html.fromHtml(spannedString)
    }
    return spannedText
}


/**
 * Extension method to get font style from path
 *
 * @param context context instance
 * @param path path of font selected
 *
 * @return spannable object
 */
fun String?.addFont(context: Context, path: String): Spannable {
    if (this == null) {
        return SpannableString("")
    }
    val spannable = SpannableString(this)
    val typefaceSpan = CalligraphyTypefaceSpan(TypefaceUtils.load(context.assets, path))
    spannable.setSpan(typefaceSpan, 0, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannable
}

/**
 * Extension method to get int value, if string is null, function return 0
 *
 * @return int value
 */
fun String?.valueToInt(): Int = this?.toInt() ?: 0