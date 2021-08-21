package com.hmelizarraraz.fintonictest.ui.utils

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.widget.TextView
import com.hmelizarraraz.fintonictest.ui.utils.Constants
import com.hmelizarraraz.fintonictest.ui.utils.Utils

/**
 * WibeAlertDialogUtils
 *
 * Class to show custom alert dialog for wibe
 *
 * @param context context instance
 * @since 01/10/2019
 */
class WibeAlertDialogUtils(private val context: Context) {

    /*
    /**
     * Method for show an alert with edit text for send quote by email
     *
     * @param defaultEmail String with email to preset in edit text
     * @param action Unit method to invoke an action after push accept button
     */
    fun showSendByEmailAlert(defaultEmail: String, action: (String) -> Unit) {

        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AppTheme))
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_with_edit_text, null)
        alertBuilder.setView(view)
        alertBuilder.setCancelable(false)
        val alertDialog = alertBuilder.create()
        view.ivAlertWithEditTextClose.setOnClickListener { alertDialog.dismiss() }
        view.tvAlertWithEditTextTitle.setText(
            Utils.getStyle(
                context.getString(R.string.my_quotes_send_email_title_alert),
                context, Constants.FONT_BOLD), TextView.BufferType.SPANNABLE)

        view.tvAlertWithEditTextMessage.setText(
            Utils.getStyle(
                context.getString(R.string.my_quotes_send_email_message_alert),
                context, Constants.FONT_MEDIUM), TextView.BufferType.SPANNABLE)

        view.etAlertWithEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        view.etAlertWithEditText.hint = context.getString(R.string.my_quotes_send_email_hint_button_alert)
        view.etAlertWithEditText.setText(defaultEmail)

        view.ibAcceptAlertWithEditText.setOnClickListener {
            alertDialog.dismiss()
            action.invoke(view.etAlertWithEditText.text.toString())
        }

        alertDialog.show()
    }*/
}