package com.hmelizarraraz.fintonictest.ui.utils

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.hmelizarraraz.fintonictest.R
import com.hmelizarraraz.fintonictest.ui.utils.extentions.spanned

/**
 * AuchAlertDialogUtils
 *
 * Class to show custom alert dialogs for auch
 *
 * @param context context instance
 */
class AuchAlertDialogUtils(private val context: Context) {

    /*/**
     * Method to show hologram alert dialog
     *
     * @param selection selection method to response hologram selected
     */
    fun showHologramAlert(selection: (String) -> Unit) {
        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.Theme_FintonicTest))
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_change_hologram, null)

        alertBuilder.setView(view)
        alertBuilder.setCancelable(false)
        val alertDialog = alertBuilder.create()
        view.ivHologramAlertClose.setOnClickListener { alertDialog.dismiss() }
        view.tvHologramAlertTitle.setText(
            Utils.getStyle(
                context.getString(R.string.auch_plate_data_title_choose_hologram),
                context, Constants.FONT_BOLD
            ), TextView.BufferType.SPANNABLE)

        var hologramSelected = ""

        view.rgHologramList.setOnCheckedChangeListener { _, checkedId ->
            hologramSelected = when (checkedId) {
                view.rbHologram00.id -> Constants.HOLOGRAM_00
                view.rbHologram0.id -> Constants.HOLOGRAM_0
                view.rbHologram1.id -> Constants.HOLOGRAM_1
                view.rbHologram2.id -> Constants.HOLOGRAM_2
                else -> ""
            }
        }
        view.ibAcceptHologram.setOnClickListener {
            if (hologramSelected != "") {
                alertDialog.dismiss()
                selection.invoke(hologramSelected)
            }
        }
        alertDialog.show()
    }

    /**
     * Method to show rates alert dialog
     *
     * @param verificationRate verification rate value
     * @param extemporaneousVerificationRate extemporaneous verification rate value
     */
    fun showHologramAlert(verificationRate: String, extemporaneousVerificationRate: String) {
        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AppTheme))
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_verification_rate, null)

        alertBuilder.setView(view)
        alertBuilder.setCancelable(false)
        val alertDialog = alertBuilder.create()

        view.tvVerificationRateTitleAlert.setText(
            Utils.getStyle(
                context.getString(R.string.verification_view_rates_verification_title_alert),
                context, Constants.FONT_BOLD
            ), TextView.BufferType.SPANNABLE)

        view.tvVerificationRateAlert.text = context.getString(R.string.verification_view_rates_verification_alert)
        view.tvVerificationRateValueAlert.setText(
            Utils.getStyle(
                verificationRate, context,
                Constants.FONT_BOLD
            ), TextView.BufferType.SPANNABLE)

        view.tvExtemporaneousVerificationRateAlert.text = context.getString(R.string.verification_view_extemporaneous_rates_verification_alert)
        view.tvExtemporaneousVerificationRateValueAlert.setText(
            Utils.getStyle(
                extemporaneousVerificationRate,
                context, Constants.FONT_BOLD
            ), TextView.BufferType.SPANNABLE)

        view.ibBackAlert.setOnClickListener { alertDialog.dismiss() }

        alertDialog.show()
    }

    /**
     * Method to show alert whit report information
     *
     * @param carHouse car house object, it can be null
     * @param linkAction action for link view
     */
    fun showCarHouseAlert(carHouse: CarYardModel?, linkAction: (() -> Unit)?) {
        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AppTheme))
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_car_report, null)

        alertBuilder.setView(view)
        alertBuilder.setCancelable(false)
        val alertDialog = alertBuilder.create()

        view.ivCloseAlert.setOnClickListener { alertDialog.dismiss() }

        view!!.tvTitle.setText(
            Utils.getStyle(
                context.getString(
                    R.string.auch_my_car_is_not_here_alert_title_report,
                    if (carHouse != null) "SI" else "NO"
                ),
                context, Constants.FONT_BOLD
            ), TextView.BufferType.SPANNABLE)
        if (carHouse != null) {
            carHasReport(view, carHouse, alertDialog)
        } else {
            carHasNotReport(view, alertDialog, linkAction)
        }

        alertDialog.show()
    }

    /**
     * Method for car has report logic
     *
     * @param view custom view
     * @param carHouse car house object
     * @param alertDialog alert dialog instance
     */
    private fun carHasReport(view: View?, carHouse: CarYardModel, alertDialog: AlertDialog) {
        view!!.tvMessage.text = context.getString(R.string.auch_my_car_is_not_here_alert_message_with_report,
                carHouse.address.notNull()).spanned()
        view.tvCaption.text = context.getString(R.string.auch_my_car_is_not_here_alert_caption_with_report)
        view.ivLink.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_how_to_get_link))

        view.ibCallCarHouses.setOnClickListener {
            alertDialog.dismiss()
            Utils.startCall(carHouse.telephone ?: Constants.CAR_YARD_GENERAL_NUMBER, context)
        }
        view.ivLink.setOnClickListener {
            alertDialog.dismiss()
            Utils.goGoogleMaps(
                carHouse.latitude.notNull().toFloat(),
                carHouse.longitude.notNull().toFloat(),
                context
            )
        }
    }

    /**
     * Method for car has report logic
     *
     * @param view custom view
     * @param alertDialog alert dialog instance
     * @param linkAction action for link view
     */
    private fun carHasNotReport(view: View?, alertDialog: AlertDialog, linkAction: (() -> Unit)?) {
        view!!.tvMessage.text = context.getString(R.string.auch_my_car_is_not_here_alert_message_without_report)
        view.tvCaption.text = context.getString(R.string.auch_my_car_is_not_here_alert_caption_without_report)
        view.ivLink.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_sos_wibe_link))

        view.ibCallCarHouses.setOnClickListener {
            alertDialog.dismiss()
            Utils.startCall(Constants.CAR_YARD_GENERAL_NUMBER, context)
        }
        view.ivLink.setOnClickListener {
            alertDialog.dismiss()
            linkAction!!.invoke()
        }
    }

    /**
     * Method to show rate app alert
     *
     * @param action action for all buttons, it return type of event
     */
    fun showRateAlert(action: (RateAppEnum) -> Unit) {
        val alertBuilder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.AppTheme))
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_rate_app, null)

        alertBuilder.setView(view)
        alertBuilder.setCancelable(false)
        val alertDialog = alertBuilder.create()

        TextViewCompat.setAutoSizeTextTypeWithDefaults(view.tvRateSubTitle, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)

        view.btRateApp.setText(
            Utils.getStyle(
                context.getString(R.string.auch_map_rate_app_rate_button),
                context, Constants.FONT_HEAVY
            ), TextView.BufferType.SPANNABLE)

        view.btRateLater.setText(
            Utils.getStyle(
                context.getString(R.string.auch_map_rate_app_rate_later_button),
                context, Constants.FONT_MEDIUM
            ), TextView.BufferType.SPANNABLE)

        view.ivRateClose.setOnClickListener { closeRateAlert(action, RateAppEnum.LATER, alertDialog) }
        view.btRateApp.setOnClickListener { closeRateAlert(action, RateAppEnum.RATE_APP, alertDialog) }
        view.btRateLater.setOnClickListener { closeRateAlert(action, RateAppEnum.LATER, alertDialog) }
        alertDialog.show()
    }

    /**
     * Method to close alert dialog
     *
     * @param action action for all buttons, it return type of event
     * @param event event to return
     * @param alertDialog alert dialog instance
     */
    private fun closeRateAlert(action: (RateAppEnum) -> Unit, event: RateAppEnum, alertDialog: AlertDialog) {
        action.invoke(event)
        alertDialog.dismiss()
    }*/

}