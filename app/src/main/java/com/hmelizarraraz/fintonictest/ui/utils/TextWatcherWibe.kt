package com.hmelizarraraz.fintonictest.ui.utils

import android.text.Editable
import android.text.TextWatcher

/**
 * TextWatcherWibe
 */
abstract class TextWatcherWibe : TextWatcher {

    /**
     * AfterTextChanged
     *
     * @param p0 editable text string
     */
    override fun afterTextChanged(p0: Editable?) {
        //empty method
    }

    /**
     * BeforeTextChanged
     *
     * @param p0 text string
     * @param p1 start int
     * @param p2 count int
     * @param p3 after int
     */
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //empty method
    }

    /**
     * TextChanged
     *
     * @param p0 charSequence text string
     * @param p1 start int
     * @param p2 before int
     * @param p3 count int
     */
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // empty method
    }




}