package com.hmelizarraraz.fintonictest.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hmelizarraraz.fintonictest.R
import com.hmelizarraraz.fintonictest.ui.utils.extentions.spanned

/**
 * AlertParams
 *
 * @param context context instance
 * @param title alert title
 * @param message alert message
 * @param acceptTitle accept title button
 * @param onAccept accept action of alert button
 * @param cancelTitle cancel title button
 * @param onCancel cancel action of alert button
 * @param onCloseAction close action of alert icon
 * @param icon icon of custom alert
 */
data class AlertParams(
        val context: Context,
        var title: String?,
        var message: String?,
        var acceptTitle: String?,
        var onAccept: (() -> Unit?)?,
        var cancelTitle: String?,
        var onCancel: (() -> Unit?)?,
        var onCloseAction: (() -> Unit?)?,
        var icon: Int = -1,
        var linkMessage: String? = null,
        var linkAction: (() -> Unit?)? = null
)

/**
 * AlertDialogUtils
 * Created by pablogutierrez on 31/12/18.
 */
class AlertDialogUtils private constructor(val alertParams: AlertParams) {

    /**
     * Method to show alert dialog
     */
    fun showAlert() {
        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(alertParams.context, R.style.Theme_FintonicTest))
        alertBuilder.setTitle(alertParams.title)
        alertBuilder.setMessage(alertParams.message)
        if (alertParams.onAccept == null) {
            alertBuilder.setPositiveButton(alertParams.acceptTitle) { dialogInterface, _ -> dialogInterface.dismiss() }
        } else {
            alertBuilder.setPositiveButton(alertParams.acceptTitle) { _, _ -> alertParams.onAccept!!.invoke() }
        }

        if (alertParams.onCancel != null) {
            alertBuilder.setNegativeButton(alertParams.cancelTitle) { _, _ -> alertParams.onCancel!!.invoke() }
        }
        alertBuilder.setCancelable(false)
        alertBuilder.create().show()
    }

    //TODO
    /**
     * Method to show custom alert dialog
     */
    /*fun showCustomAlert() {
        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(alertParams.context, R.style.Theme_FintonicTest))
        val layoutInflater = alertParams.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_generic_message, null)

        alertBuilder.setView(view)
        alertBuilder.setCancelable(false)
        val alertDialog = alertBuilder.create()

        if (alertParams.icon != -1) {
            view.ivAlertIcon.visibility = View.VISIBLE
            view.ivAlertIcon.setImageDrawable(ContextCompat.getDrawable(alertParams.context, alertParams.icon))
        } else {
            view.ivAlertIcon.visibility = View.GONE
        }

        configAlertMessages(view, alertDialog)
        configAlertButtons(view, alertDialog)
        alertDialog.show()
    }

    /**
     * Method to config alert messages
     *
     * @param view alert view
     */
    private fun configAlertMessages(view: View, alertDialog: AlertDialog) {
        view.tvAlertTitle.setText(
            Utils.getStyle(
                alertParams.title!!, alertParams.context,
                Constants.FONT_BOLD
            ), TextView.BufferType.SPANNABLE)
        view.tvAlertMessage.text = alertParams.message

        if(alertParams.linkMessage != null){
            view.tvAlertLink.visibility = View.VISIBLE
            view.tvAlertLink.text = alertParams.linkMessage.spanned()
            if(alertParams.linkAction != null){
                view.tvAlertLink.setOnClickListener {
                    alertDialog.dismiss()
                    alertParams.linkAction!!.invoke()
                }
            }
        }
    }

    /**
     * Method to config alert buttons
     *
     * @param view alert view
     * @param alertDialog alert dialog instance
     */
    private fun configAlertButtons(view: View, alertDialog: AlertDialog) {
        if (alertParams.onCloseAction != null) {
            view.ivAlertClose.visibility = View.VISIBLE
            view.ivAlertClose.setOnClickListener {
                alertDialog.dismiss()
                alertParams.onCloseAction!!.invoke()
            }
        }

        if (alertParams.onAccept == null) {
            view.btAlertAccept.text = "Aceptar"
            view.btAlertAccept.setOnClickListener { alertDialog.dismiss() }
        } else {
            view.btAlertAccept.text = alertParams.acceptTitle
            view.btAlertAccept.setOnClickListener {
                alertDialog.dismiss()
                alertParams.onAccept!!.invoke()
            }
        }
        if (alertParams.cancelTitle == null || alertParams.cancelTitle == "") {
            view.btAlertCancel.visibility = View.GONE
        }else{
            view.btAlertCancel.text = alertParams.cancelTitle
            view.btAlertCancel.visibility = View.VISIBLE
            if (alertParams.onCancel == null){
                view.btAlertCancel.setOnClickListener { alertDialog.dismiss() }
            } else {
                view.btAlertCancel.setOnClickListener {
                    alertDialog.dismiss()
                    alertParams.onCancel!!.invoke()
                }
            }
        }
    }*/

    /**
     * Builder class of alert dialog
     *
     * @param context context instance
     */
    class Builder(private val context: Context) {

        /**
         * Title of alert dialog
         */
        private var mTitle: String? = ""
        /**
         * Icon of custom alert dialog
         */
        private var mIcon: Int = -1
        /**
         * Message of alert dialog
         */
        private var mMessage: String? = ""
        /**
         * Accept title button
         */
        private var mAcceptTitle: String? = ""
        /**
         * String for text of link
         */
        private var mLink: String? = ""
        /**
         * Accept action of alert button
         */
        private var mOnAcceptAction: (() -> Unit?)? = null
        /**
         * Cancel title button
         */
        private var mCancelTitle: String? = ""
        /**
         * Cancel action of alert button
         */
        private var mOnCancelAction: (() -> Unit?)? = null
        /**
         * Close action of alert icon
         */
        private var mOnCloseAction: (() -> Unit?)? = null
        /**
         * Link action of alert
         */
        private var mOnLinkAction: (() -> Unit?)? = null

        /**
         * Method to set title of alert dialog
         *
         * @param title title of alert dialog
         *
         * @return this
         */
        fun setTitle(title: String): Builder {
            this.mTitle = title
            return this
        }

        /**
         * Method to set icon of alert dialog
         *
         * @param icon icon of custom alert dialog
         *
         * @return this
         */
        fun setIcon(icon: Int): Builder {
            this.mIcon = icon
            return this
        }

        /**
         * Method to set message of alert dialog
         *
         * @param message message of alert dialog
         *
         * @return this
         */
        fun setMessage(message: String): Builder {
            this.mMessage = message
            return this
        }

        /**
         * Method to set accept button
         *
         * @param title accept title button
         * @param onAccept accept action of alert button
         *
         * @return this
         */
        fun setAccept(title: String, onAccept: () -> Unit?): Builder {
            this.mAcceptTitle = title
            this.mOnAcceptAction = onAccept
            return this
        }

        /**
         * Method to set cancel button
         *
         * @param title cancel title button
         * @param onCancel cancel action of alert button
         *
         * @return this
         */
        fun setCancel(title: String, onCancel: () -> Unit?): Builder {
            this.mCancelTitle = title
            this.mOnCancelAction = onCancel
            return this
        }

        /**
         * Method to set close icon action of custom alert dialog
         *
         * @param onClose close action of alert icon
         *
         * @return this
         */
        fun setCloseAction(onClose: () -> Unit?): Builder {
            this.mOnCloseAction = onClose
            return this
        }

        /**
         * Method to set text of link and its action
         *
         * @param link text of link
         * @param onLink on link action
         *
         * @return this
         */
        fun setLinkAction(link: String, onLink: () -> Unit?): Builder {
            this.mLink = link
            this.mOnLinkAction = onLink
            return this
        }

        /**
         * Method to show alert dialog
         */
        fun showAlert() {
            val alertParams =
                    AlertParams(context, mTitle, mMessage, mAcceptTitle, mOnAcceptAction, mCancelTitle,
                            mOnCancelAction, mOnCloseAction)
            AlertDialogUtils(alertParams).showAlert()
        }

        /**
         * Method to show custom alert dialog
         */
        /*fun showCustomAlert() {
            val alertParams =
                    AlertParams(context, mTitle, mMessage, mAcceptTitle, mOnAcceptAction, mCancelTitle,
                            mOnCancelAction, mOnCloseAction, mIcon, mLink, mOnLinkAction)
            AlertDialogUtils(alertParams).showCustomAlert()
        }*/

        /**
         * Method to build alert dialog
         *
         * @return alert dialog instance
         */
        fun build(): AlertDialogUtils {
            val alertParams =
                    AlertParams(context, mTitle, mMessage, mAcceptTitle, mOnAcceptAction, mCancelTitle,
                            mOnCancelAction, mOnCloseAction, mIcon)
            return AlertDialogUtils(alertParams)
        }
    }

}