package com.hmelizarraraz.fintonictest.ui.utils.extentions

import android.widget.TextView

fun TextView.string(): String = if (this.text != null) this.text.toString() else ""