package com.ciompa.cleverlance.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapWorker {

    fun decodeSampledBitmapFromResource(image: ByteArray, reqWidth: Int, reqHeight: Int): Bitmap {

        // First decode with inJustDecodeBounds=true to check dimensions
        val opts = BitmapFactory.Options()
        opts.inJustDecodeBounds = true

        BitmapFactory.decodeByteArray(image, 0, image.size, opts)

        // Calculate inSampleSize
        opts.inSampleSize = calculateInSampleSize(opts, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        opts.inJustDecodeBounds = false

        return BitmapFactory.decodeByteArray(image, 0, image.size, opts)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}