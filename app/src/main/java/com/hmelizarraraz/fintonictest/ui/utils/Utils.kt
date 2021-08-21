package com.hmelizarraraz.fintonictest.ui.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.util.TypedValue
import uk.co.chrisjenx.calligraphy.TypefaceUtils
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan
import android.text.SpannableString
import android.text.Spannable
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavOptions
import com.hmelizarraraz.fintonictest.BuildConfig
import com.hmelizarraraz.fintonictest.R
import java.io.File
import java.io.IOException

/**
 * Utils
 */
object Utils {

    /**
     * Method to close keyboard
     *
     * @param activity activity instance
     */
    fun closeKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Method to close keyboard
     *
     * @param activity activity instance
     * @param input input field
     */
    fun closeKeyboard(activity: Activity, input: EditText) {
        val keyboard = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.hideSoftInputFromWindow(input.windowToken, 0)
    }

    /**
     * Method to show keyboard
     *
     * @param activity activity instance
     * @param editText field for show keyboard
     */
    fun showKeyboard(activity: Activity, editText: EditText) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.SHOW_IMPLICIT)
        editText.requestFocus()
        editText.setSelection(editText.text.length)
    }

    /**
     * Method to validate if
     *
     * application has permissions
     *
     * @param context     context instance
     * @param permissions permissions list
     * @return boolean
     */
    fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    /**
     * Method to get strings from resources
     *
     * @param title title value
     * @param context context instance
     *
     * @return message from strings
     */
    fun findTitleString(title: String?, context: Context): String {
        val validTitle = if (title == null || title == "") "text_alert" else title
        val newTitle = findStringFromResources(validTitle, context)
        return if (newTitle == "" || newTitle.contains("_"))
            findStringFromResources("text_alert", context) else newTitle
    }

    /**
     * Method to get strings from resources
     *
     * @param message message or code
     * @param context context instance
     *
     * @return message from strings
     */
    fun findString(message: String?, context: Context): String {
        val validMessage =
                if (message == null || message == "") "error_message_networkerrorenum_server" else message
        return findStringFromResources(validMessage, context)
    }

    /**
     * Method to get strings of forms from resources
     *
     * @param message message or code
     * @param context context instance
     *
     * @return message from strings
     */
    fun findFormString(message: String?, context: Context): String? =
            if (message == null) null else findStringFromResources(message, context)

    /**
     * Method to find strings from resources
     *
     * @param message message or code
     * @param context context instance
     *
     * @return message from strings
     */
    private fun findStringFromResources(message: String, context: Context): String = try {
        val packageName = context.packageName
        val resId = context.resources.getIdentifier(message.toLowerCase(), "string", packageName)
        context.getString(resId)
    } catch (e: Exception) {
        message
    }

    /**
     * Method to get tooltip error
     *
     * @param activity activity instance
     * @param anchor view to position tooltip
     * @param rootView view container
     */
    //TODO
    /*fun getTooltipError(activity: Activity, anchor: View, rootView: View): TooltipComponent =
            TooltipComponent.Builder(activity, anchor)
                    .setRootView(rootView)
                    .setGravity(Gravity.TOP)
                    .build()*/

    /**
     * Method to get string converter to spanned
     *
     * @param text text to convert
     */
    fun getStringToSpaned(text: String): Spanned =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(text)
            }

    /**
     * Method to get string on validation for fraction paragraph regulation
     *
     * @param context context instance
     * @param stringId string id resource
     * @param text text to validate
     *
     * @return string format
     */
    fun getFractionParagraph(context: Context, stringId: Int, text: String): String =
            if (text.isEmpty()) "" else context.getString(stringId, text)

    /**
     * Method to get label for regulation penalty
     *
     * @param context context instance
     * @param penalty flag for penalty
     *
     * @return string to label
     */
    /*
    fun getPenaltyLabel(context: Context, penalty: Boolean?): String =
            if (penalty != null && penalty) {
                context.getString(R.string.auch_regulation_penalty)
            } else {
                context.getString(R.string.auch_regulation_not_penalty)
            }*/

    /**
     * Method to start call
     *
     * @param phoneNumber phone number to call
     * @param context context instance
     */
    fun startCall(phoneNumber: String, context: Context) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
        context.startActivity(intent)
    }

    /**
     * Method to get pixes from dps
     *
     * @param dps     dps
     * @param context context instance
     * @return int
     */
    fun getPixelsFromDPs(dps: Float, context: Context): Float =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, context.resources.displayMetrics)

    /**
     * Method to get text with style
     *
     * @param text    text
     * @param context context instance
     * @param path    path of styles
     * @return spannable
     */
    fun getStyle(text: String, context: Context, path: String): Spannable {
        val spannable = SpannableString(text)
        val typefaceSpan = CalligraphyTypefaceSpan(TypefaceUtils.load(context.assets, path))
        spannable.setSpan(typefaceSpan, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }

    /**
     * Method to go google maps
     *
     * @param uri uri param
     * @param context context instance
     */
    fun goGoogleMaps(latitude: Float, longitude: Float, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=$latitude,$longitude"))
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    /**
     * Method to go google maps for a specific address
     *
     * @param address query string for address
     * @param context context instance
     */
    fun goGoogleMapsByAddress(address: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=$address"))
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    /**
     * Method to go play store
     *
     * @param context context instance
     */
    fun goPlayStore(context: Context) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.bancomer.bbva.wibe")))
    }

    /**
     * Method to get back options configuration
     *
     * @return nav options
     */
    fun backOptionNavGraph(): NavOptions {
        val navOptions = NavOptions.Builder()
        navOptions.setLaunchSingleTop(true)
        navOptions.setEnterAnim(R.anim.slide_in_left)
        navOptions.setExitAnim(R.anim.slide_out_right)
        navOptions.setPopEnterAnim(R.anim.slide_in_left)
        navOptions.setPopExitAnim(R.anim.slide_out_right)
        //navOptions.setPopUpTo(R.id.auchHomeFragment, false)
        return navOptions.build()
    }

    /**
     * Method to get navigation options for hamburger menu
     *
     * @return nav options
     */
    fun getHMNavGraphOptions(): NavOptions {
        val navOptions = NavOptions.Builder()
        navOptions.setLaunchSingleTop(true)
        navOptions.setEnterAnim(R.anim.slide_in_right)
        navOptions.setExitAnim(R.anim.slide_out_left)
        navOptions.setPopEnterAnim(R.anim.slide_in_left)
        navOptions.setPopExitAnim(R.anim.slide_out_right)
        return navOptions.build()
    }

    /**
     * Method to get navigation options for hamburger menu
     *
     * @return nav options
     */
    fun sinisterNavGraphOptions(): NavOptions {
        val navOptions = NavOptions.Builder()
        navOptions.setLaunchSingleTop(true)
        navOptions.setPopEnterAnim(R.anim.slide_in_left)
        navOptions.setPopExitAnim(R.anim.slide_out_right)
        return navOptions.build()
    }

    /**
     * Method to set margins in view
     *
     * @param v view to configure
     * @param l left value
     * @param t top value
     * @param r right value
     * @param b bottom value
     */
    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        val newLayoutParams = v.layoutParams as ConstraintLayout.LayoutParams
        newLayoutParams.leftMargin = l
        newLayoutParams.topMargin = t
        newLayoutParams.rightMargin = r
        newLayoutParams.bottomMargin = b
        v.layoutParams = newLayoutParams
        v.requestLayout()
    }

    /**
     * Method to go pdf file
     *
     * @param file document file
     * @param context context instance
     */
    fun goToPdfDocument(file: File, context: Context) {
        try {
            val pdfIntent = Intent(Intent.ACTION_VIEW)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file)
                pdfIntent.setDataAndType(uri, "application/pdf")
            } else {
                val uri = Uri.fromFile(file)
                pdfIntent.setDataAndType(uri, "application/pdf")
            }
            context.startActivity(pdfIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, context.getString(R.string.home_open_file_error), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Method to open xml file
     *
     * @param file file with xml path
     * @param context context instance
     */
    fun openXmlFile(file: File, context: Context) {
        try {
            val uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file)
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.type = "application/xml"
            context.startActivity(Intent.createChooser(intent, "Compartir..."))
        } catch (e: IOException) {
            Toast.makeText(context, context.getString(R.string.error_message_apperrorenum_document_error), Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * Method to get drawer menu items
     *
     * @return list of drawer menu items
     */
    /*
    fun getDrawerMenuItems(context: Context): MutableList<DrawerMenuItem> =
            mutableListOf(
                    DrawerMenuItem(
                            HomeMenuEnum.HOME_OPTION.ordinal, R.drawable.ic_drawer_menu_home,
                            context.getString(R.string.hamburger_menu_item_home), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.SOS_OPTION.ordinal, R.drawable.ic_drawer_menu_sos,
                            context.getString(R.string.hamburger_menu_item_sos), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.MORE_PRODUCTS.ordinal, R.drawable.ic_drawer_menu_more_products,
                            context.getString(R.string.hamburger_menu_item_more_products), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.SERVICES.ordinal, R.drawable.ic_drawer_menu_services,
                            context.getString(R.string.hamburger_menu_item_services), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.PROMOTIONS.ordinal, R.drawable.ic_drawer_menu_promotions,
                            context.getString(R.string.hamburger_menu_item_promotions), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.MEET_WIBE.ordinal, R.drawable.ic_drawer_menu_meet_wibe,
                            context.getString(R.string.hamburger_menu_item_meet_wibe), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.CONTACT_OPTION.ordinal, R.drawable.ic_drawer_menu_contact,
                            context.getString(R.string.hamburger_menu_item_contact), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.TYC_OPTION.ordinal, R.drawable.ic_drawer_menu_tyc,
                            context.getString(R.string.hamburger_menu_item_tyc), OptionTypeDrawerMenuEnum.GUESS
                    ),
                    DrawerMenuItem(-1, 0, null, OptionTypeDrawerMenuEnum.BORDER),
                    DrawerMenuItem(
                            HomeMenuEnum.PROFILE_OPTION.ordinal, R.drawable.ic_drawer_menu_profile,
                            context.getString(R.string.hamburger_menu_profile), OptionTypeDrawerMenuEnum.CLIENT
                    ),
                    DrawerMenuItem(
                            HomeMenuEnum.MY_POLICIES.ordinal, R.drawable.ic_drawer_menu_my_policies,
                            context.getString(R.string.hamburger_menu_my_policies), OptionTypeDrawerMenuEnum.CLIENT
                    )
            )*/

    /**
     * Method to get bearing of "begin" and "end" coordinates
     *
     * @param begin begin coordinates
     * @param end end coordinates
     *
     * @return bearing value
     */
    /*
    fun getBearing(begin: LatLng, end: LatLng): Float {
        val lat = Math.abs(begin.latitude - end.latitude)
        val lng = Math.abs(begin.longitude - end.longitude)

        return when {
            begin.latitude < end.latitude && begin.longitude < end.longitude -> (Math.toDegrees(Math.atan(lng / lat)) + 180).toFloat()
            begin.latitude >= end.latitude && begin.longitude < end.longitude -> (90 - Math.toDegrees(Math.atan(lng / lat)) + 270).toFloat()
            begin.latitude >= end.latitude && begin.longitude >= end.longitude -> Math.toDegrees(Math.atan(lng / lat)).toFloat()
            begin.latitude < end.latitude && begin.longitude >= end.longitude -> (90 - Math.toDegrees(Math.atan(lng / lat)) + 90).toFloat()
            else -> (90 - Math.toDegrees(Math.atan(lng / lat)) + 270).toFloat()
        }
    }*/

    /**
     * Method to send email
     *
     * @param uri uri information
     * @param activity activity instance
     */
    fun sendEmail(uri: Uri, activity: Activity) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.data = uri
        activity.startActivity(Intent.createChooser(intent, "Send mail..."))
    }

    /**
     * Method to send whats app message
     *
     * @param message message to share
     * @param activity activity instance
     */
    fun sendWhatsAppMessage(message: String, activity: Activity) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        val pm = activity.packageManager
        try {
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
            intent.setPackage("com.whatsapp")
            intent.putExtra(Intent.EXTRA_TEXT, message)
            activity.startActivity(Intent.createChooser(intent, "Share with"))
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(activity, "WhatsApp no esta instalado", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Method to get address object from lat lng values and geo coder instance
     *
     * @param latLng lat/lng object, it can be null
     * @param geoCoder geo coder object instance
     *
     * @return address object, it can be null
     */
    /*
    fun getAddress(latLng: LatLng?, geoCoder: Geocoder): Address? {
        latLng?.let { coordinates ->
            return try {
                val addressList = geoCoder.getFromLocation(coordinates.latitude, coordinates.longitude, 1)
                addressList[0]
            } catch (e: Exception) {
                null
            }
        }
        return null
    }*/

}