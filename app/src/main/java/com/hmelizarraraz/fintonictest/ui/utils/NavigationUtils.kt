package com.hmelizarraraz.fintonictest.ui.utils

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import android.util.Pair
import androidx.annotation.RequiresApi
import com.hmelizarraraz.fintonictest.R

/**
 * IntentModel
 *
 * @param activity activity instance
 * @param _class class to move
 * @param flags flags intent
 * @param params params to share
 * @param code code value
 * @param transition transition intent
 * @param shareElements share elements
 */
data class IntentModel(
    val activity: Activity,
    val _class: Class<*>,
    var flags: Int,
    var params: HashMap<String, String>?,
    val code: Int, var fragment: Fragment?,
    val transition: NavigationUtils.Transition,
    var shareElements: Pair<View, String>?
)

/**
 * NavigationUtils
 * Created by pablogutierrez on 11/02/19.
 */
class NavigationUtils private constructor(private val intentModel: IntentModel) {

    /**
     * Transition
     */
    enum class Transition {
        FADE,
        SLIDE_IN,
        SLIDE_OUT,
        NONE;
    }

    /**
     * Init method
     */
    init {
        launchIntent()
    }

    /**
     * Method to launch intent
     */
    private fun launchIntent() {
        val intent = Intent(intentModel.activity, intentModel._class)
        if (intentModel.flags > 0) {
            intent.flags = intentModel.flags
        }

        if (intentModel.params != null) {
            for (entry in intentModel.params!!) {
                intent.putExtra(entry.key, entry.value)
            }
        }
        if (intentModel.shareElements == null) {
            startActivity(intent)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivityTransition(intent)
            } else {
                startActivity(intent)
            }
        }
    }

    /**
     * Method to start activity
     *
     * @param intent intent object
     */
    private fun startActivity(intent: Intent) {
        if (intentModel.code > 0) {
            if (intentModel.fragment == null) {
                intentModel.activity.startActivityForResult(intent, intentModel.code)
            } else {
                intentModel.fragment!!.startActivityForResult(intent, intentModel.code)
            }
        } else if (intentModel.code == 0) {
            intentModel.activity.startActivity(intent)
        }
        setTransition()
    }

    /**
     * Method to start activity with transition
     *
     * @param intent intent object
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startActivityTransition(intent: Intent) {
        val options = ActivityOptions.makeSceneTransitionAnimation(intentModel.activity, intentModel.shareElements)
        intentModel.activity.startActivity(intent, options.toBundle())
    }

    /**
     * Method to set transition
     */
    private fun setTransition() {
        when (intentModel.transition) {
            Transition.FADE -> {
                intentModel.activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
            Transition.SLIDE_IN -> {
                intentModel.activity.overridePendingTransition(R.anim.slide_in_right_activity, R.anim.slide_out_left_activity)
            }
            Transition.SLIDE_OUT -> {
                intentModel.activity.overridePendingTransition(R.anim.slide_in_left_activity, R.anim.slide_out_right_activity)
            }
            Transition.NONE -> {
                intentModel.activity.overridePendingTransition(0, 0)
            }
        }
    }

    /**
     * Builder class
     * @param activity current activity
     * @param _class class to move
     */
    class Builder(private val activity: Activity, private val _class: Class<*>) {

        /**
         * Flags intent
         */
        private var mFlags: Int = 0
        /**
         * Params to share
         */
        private var mParams: HashMap<String, String>? = null
        /**
         * Code value
         */
        private var mCode: Int = 0
        /**
         * Fragment instance
         */
        private var mFragment: Fragment? = null
        /**
         * Transition intent
         */
        private var mTransition = Transition.NONE
        /**
         * Share elements
         */
        private var shareElements: Pair<View, String>? = null

        /**
         * Method to set flags to config intent
         *
         * @param flags to config intent
         *
         * @return this
         */
        fun setFlags(flags: Int): Builder {
            this.mFlags = flags
            return this
        }

        /**
         * Method to set params to send to another view
         *
         * @param params to send to another view
         *
         * @return this
         */
        fun setParams(params: HashMap<String, String>?): Builder {
            this.mParams = params
            return this
        }

        /**
         * Method to set code
         *
         * @param code code value
         *
         * @return this
         */
        fun setCode(code: Int): Builder {
            this.mCode = code
            return this
        }

        /**
         * Method to set fragment
         *
         * @param fragment fragment instance
         *
         * @return this
         */
        fun setFragment(fragment: Fragment?): Builder {
            this.mFragment = fragment
            return this
        }

        /**
         * Method to set transition
         *
         * @param transition transition type
         *
         * @return this
         */
        fun setTransition(transition: Transition): Builder {
            this.mTransition = transition
            return this
        }

        /**
         * Method to set params transition
         *
         * @param shareElements share elements for transition
         *
         * @return this
         */
        fun setParamsTransitionAnimation(shareElements: Pair<View, String>?): Builder {
            this.shareElements = shareElements
            return this
        }

        /**
         * Method to start intent
         */
        fun start() {
            val intentModel =
                IntentModel(activity, _class, mFlags, mParams, mCode, mFragment, mTransition, shareElements)
            NavigationUtils(intentModel)
        }

    }

}