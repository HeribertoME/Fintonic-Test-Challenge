package com.hmelizarraraz.fintonictest.ui.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import com.hmelizarraraz.fintonictest.R

/**
 * ProfileAlertDialogUtils
 *
 * Class to show custom alert dialog for profile
 *
 * @param context context instance
 */
class ProfileAlertDialogUtils(private val context: Context) {

    /*
    /**
     * Method to show chooser photo for profile alert dialog
     *
     * @param onCamera Method to response camera selected
     * @param onGallery Method to response gallery selected
     */
    fun showPhotoChooserAlert(onCamera: () -> Unit?, onGallery: () -> Unit) {
        val chooserBuilder = AlertDialog.Builder(ContextThemeWrapper(context, R.style.Theme_FintonicTest))
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.alert_chooser_photo, null)

        chooserBuilder.setView(view)
        val alertDialog = chooserBuilder.create()
        alertDialog.setCancelable(false)
        view.tvChooserAlertTitle.setText(
            Utils.getStyle(
                context.getString(R.string.profile_chooser_title),
                context, Constants.FONT_BOLD
            ), TextView.BufferType.SPANNABLE
        )

        view.btAlertCamera.setOnClickListener {
            onCamera.invoke()
            alertDialog.dismiss()
        }

        view.btAlertGallery.setOnClickListener {
            onGallery.invoke()
            alertDialog.dismiss()
        }

        view.ivChooserAlertClose.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }*/

}