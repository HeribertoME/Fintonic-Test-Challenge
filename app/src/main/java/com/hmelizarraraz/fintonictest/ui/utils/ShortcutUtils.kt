package com.hmelizarraraz.fintonictest.ui.utils

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.content.res.Configuration
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import com.hmelizarraraz.fintonictest.R

/**
 * ShortcutIntentModel
 *
 * @param _class class for intent
 * @param params params to share for intent
 */
data class ShortcutIntentModel(
    val _class: Class<*>,
    val param: Int?
)
/**
 * ShortcutUtils
 *
 * @param context context instance
 * @since 20/11/2019
 * @author Heriberto Martinez
 */
@RequiresApi(Build.VERSION_CODES.N_MR1)
class ShortcutUtils constructor(val context: Context) {
    /*

    /**
     * Id for shortcut
     */
    private var mId: String? =null
    /**
     * Short label for shortcut
     */
    private var mShortLabel: CharSequence? = null
    /**
     * Long label for shortcut
     */
    private var mLongLabel: CharSequence? = null
    /**
     * Icon for normal theme mode
     */
    private var mLightIcon: Int = -1
    /**
     * Icon for dark theme mode
     */
    private var mDarkIcon: Int = -1
    /**
     * List of intents from shortcut
     */
    private var mIntentList: List<ShortcutIntentModel>? = null
    /**
     * Shortcuts list
     */
    private var mShortcuts: MutableList<ShortcutInfo> = mutableListOf()

    /**
     * Method to add a new shortcut
     *
     * @param idOption Id for shortcut
     * @param shortLabel String for short label
     * @param longLabel String for long label
     * @param lightIcon Icon for normal theme
     * @param darkIcon Icon for dark theme
     * @param intentList Intent list
     *
     * @return this
     */
    fun addShortcut(
        idOption: String,
        shortLabel: CharSequence,
        longLabel: CharSequence,
        lightIcon: Int,
        darkIcon: Int,
        intentList: List<ShortcutIntentModel>
    ): ShortcutUtils {
        this.mId = idOption
        this.mShortLabel = shortLabel
        this.mLongLabel = longLabel
        this.mLightIcon = lightIcon
        this.mDarkIcon = darkIcon
        this.mIntentList = intentList
        buildShortcut()
        return this
    }

    /**
     * Method for build shortcut info and add to shortcut list
     */
    private fun buildShortcut() {
        val shortcut = ShortcutInfo.Builder(context, mId)
            .setShortLabel(mShortLabel!!)
            .setLongLabel(mLongLabel!!)
            .setIcon(Icon.createWithResource(context, getIcon()))
            .setIntents(getIntents())
            .build()

        mShortcuts.add(shortcut)
    }

    /**
     * Method to get icon for normal theme or dark theme
     *
     * @return icon resource
     */
    private fun getIcon():Int {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> return mLightIcon
            Configuration.UI_MODE_NIGHT_NO -> return mDarkIcon
            else -> mLightIcon
        }
    }

    /**
     * Method to get intents
     *
     * @return Array of intents
     */
    private fun getIntents(): Array<Intent?> {
        val intents = arrayOfNulls<Intent>(mIntentList!!.size)
        for (i in mIntentList!!.indices) {
            intents[i] = Intent(context, mIntentList!![i]._class).apply {
                action = Intent.ACTION_VIEW
                if (mIntentList!![i].param != null) {
                    putExtra(Constants.SHORTCUT_EXTRA_INTENT_KEY, mIntentList!![i].param)
                    putExtra(Constants.SHORTCUT_TYPE_INTENT_KEY, FirstMenuTagEnum.SHORTCUT.ordinal)
                }
            }
        }
        return intents
    }

    /**
     * Method to initialize shortcut manager and add shortcuts
     */
    fun init() {
        val shortcutManager = context.getSystemService<ShortcutManager>(ShortcutManager::class.java)
        if (shortcutManager != null) {
            shortcutManager.dynamicShortcuts = mShortcuts
        }
    }

    /**
     * Method to add sos and quote shortcuts
     */
    fun createSOSQuoteShortcuts() {
        addShortcut(
                Constants.SHORTCUT_ID_OPTION_QUOTE,
                context.getString(R.string.shortcut_quote_label),
                context.getString(R.string.shortcut_quote_label),
                R.drawable.ic_shortcut_quote_white,
                R.drawable.ic_shortcut_quote_black,
                listOf(
                    ShortcutIntentModel(WelcomeController::class.java, null),
                    ShortcutIntentModel(HomeController::class.java, HomeMenuEnum.MORE_PRODUCTS.ordinal)
                )
            )
            .addShortcut(
                Constants.SHORTCUT_ID_OPTION_SOS,
                context.getString(R.string.shortcut_sos_label),
                context.getString(R.string.shortcut_sos_label),
                R.drawable.ic_shortcut_sos_white,
                R.drawable.ic_shortcut_sos_black,
                listOf(
                    ShortcutIntentModel(WelcomeController::class.java, null),
                    ShortcutIntentModel(HomeController::class.java, HomeMenuEnum.SOS_OPTION.ordinal)
                )
            )
            .init()
    }*/
}