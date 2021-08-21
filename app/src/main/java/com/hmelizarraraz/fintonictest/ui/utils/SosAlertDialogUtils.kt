package com.hmelizarraraz.fintonictest.ui.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.hmelizarraraz.fintonictest.R
import com.hmelizarraraz.fintonictest.ui.utils.extentions.spanned

/**
 * SosAlertDialogUtils
 *
 * @param activity activity instance
 * @param builder builder instance
 */
class SosAlertDialogUtils private constructor(
        private val activity: Activity, private val builder: Builder
) {

    /*
    /**
     * This method run when instance class is created
     */
    init {
        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(activity, R.style.Theme_FintonicTest))
        val layoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_generic_alert_sos, null)
        alertBuilder.setView(view)
        alertBuilder.setCancelable(false)

        val alertDialog = alertBuilder.create()

        if (builder.icon > 0) {
            view.ivSosIcon.setImageDrawable(ContextCompat.getDrawable(activity, builder.icon))
        }
        builder.title?.let {
            view.tvSosTitle.text = it.spanned()
        }
        builder.message?.let {
            view.tvSosMessage.text = it.spanned()
        }
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(view.tvSosMessage, 8, 15, 1, TypedValue.COMPLEX_UNIT_SP)

        view.ivSosClose.setOnClickListener {
            alertDialog.dismiss()
            builder.closeAction?.invoke()
        }

        alertDialog.show()
    }*/

    /**
     * Builder
     *
     * This class has all parameters to build custom alert for sos module
     *
     * @param activity activity instance
     */
    class Builder(private val activity: Activity) {

        /**
         * Icon value
         */
        var icon: Int = 0
        /**
         * Title value
         */
        var title: String? = null
        /**
         * Message value
         */
        var message: String? = null
        /**
         * Action of close button
         */
        var closeAction: (() -> Unit)? = null

        /**
         * Method to set icon resource id
         *
         * @param icon icon resource id
         *
         * @return this
         */
        fun setIcon(icon: Int): Builder = apply {
            this.icon = icon
        }

        /**
         * Method to set title string
         *
         * @param title title value
         *
         * @return this
         */
        fun setTitle(title: String): Builder = apply {
            this.title = title
        }

        /**
         * Method to set message string
         *
         * @param message message value
         *
         * @return this
         */
        fun setMessage(message: String): Builder = apply {
            this.message = message
        }

        /**
         * Method to set close action
         *
         * @param closeAction close action
         *
         * @return this
         */
        fun setCloseAction(closeAction: () -> Unit): Builder = apply {
            this.closeAction = closeAction
        }

        /**
         * Method to build sos alert dialog utils class
         *
         * @return sos alert dialog utils instance
         */
        fun build(): SosAlertDialogUtils = SosAlertDialogUtils(activity, this@Builder)

    }

}