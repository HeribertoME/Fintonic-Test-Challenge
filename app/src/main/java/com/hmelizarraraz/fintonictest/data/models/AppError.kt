package com.hmelizarraraz.fintonictest.data.models

/**
 * AppError model class to errors
 *
 * @param title error title
 * @param message error message
 * @param messageDebug message to show in debug console
 */
data class AppError(val title: String? = null, val message: String? = null, val messageDebug: String? = null)