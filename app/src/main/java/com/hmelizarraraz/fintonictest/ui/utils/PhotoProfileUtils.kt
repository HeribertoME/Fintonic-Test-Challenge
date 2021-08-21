package com.hmelizarraraz.fintonictest.ui.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.io.InputStream

/**
 * PhotoProfileUtils
 *
 * @since 29/10/2019
 */
class PhotoProfileUtils {

    companion object {

        /**
         * Method to launch camera intent
         *
         * @param fragment Fragment where start activity for result call
         * @param fileName File name for image
         */
        fun launchCamera(fragment: Fragment, fileName: String) {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.putExtra(
                MediaStore.EXTRA_OUTPUT,
                getCacheImagePath(fileName, fragment.requireActivity())
            )
            if (takePictureIntent.resolveActivity(fragment.requireActivity().packageManager) != null) {
                fragment.startActivityForResult(takePictureIntent, Constants.REQUEST_CODE_CAMERA)
            }
        }

        /**
         * Method to launch gallery intent
         *
         * @param fragment Fragment where start activity for result call
         */
        fun launchGallery(fragment: Fragment) {
            val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            fragment.startActivityForResult(pickPhoto, Constants.REQUEST_CODE_GALLERY)
        }

        /**
         * Method to get uri cache image path for image taken on camera
         *
         * @param fileName Name for new file
         * @param activity Activity instance
         *
         * @return New image created uri
         */
        fun getCacheImagePath(fileName: String, activity: Activity): Uri? {
            val path = File(activity.externalCacheDir, "camera")
            if (!path.exists()) path.mkdirs()
            val image = File(path, fileName)
            return FileProvider.getUriForFile(activity, activity.packageName + ".provider", image)
        }

        /**
         * Method to save image from gallery to cache
         *
         * @param inputStream Input stream from gallery image
         * @param activity Activity instance
         *
         * @return New image created uri
         */
        fun saveImageToCache(inputStream: InputStream?, activity: Activity): Uri? {
            val fileName = System.currentTimeMillis().toString() + ".jpg"
            val path = File(activity.externalCacheDir, "camera")
            if (!path.exists()) path.mkdirs()
            val image = File(path, fileName)

            image.copyInputStreamToFile(inputStream!!)

            return FileProvider.getUriForFile(activity, activity.packageName + ".provider", image)
        }

        /**
         * Method to copy input stream to a file object
         *
         * @param inputStream Input stream instance
         */
        private fun File.copyInputStreamToFile(inputStream: InputStream) {
            inputStream.use { input ->
                this.outputStream().use { fileOut ->
                    input.copyTo(fileOut)
                }
            }
        }

    }
}