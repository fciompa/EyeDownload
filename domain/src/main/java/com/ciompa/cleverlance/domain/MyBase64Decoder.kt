package com.ciompa.cleverlance.domain

import android.os.Build
import java.util.*

class MyBase64Decoder(private val imageStr: String) {
    fun decode(): ByteArray {
        var imageByteArray: ByteArray

        if (imageStr.isBlank()) {
            imageByteArray = ByteArray(0)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            imageByteArray = Base64.getDecoder().decode(imageStr)
        } else {
            imageByteArray = android.util.Base64.decode(imageStr, android.util.Base64.DEFAULT);
        }

        return imageByteArray
    }
}