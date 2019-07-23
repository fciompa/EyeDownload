package com.ciompa.cleverlance.domain

import android.os.Build
import com.ciompa.cleverlance.common.DownloadPictureError
import com.ciompa.cleverlance.common.HashUtils
import com.ciompa.cleverlance.repository.Repository
import java.util.*

class DomainImp(private val repository: Repository) : Domain {

    override suspend fun downloadPicture(userName: String, password: String): DownloadPictureError {
        val authorization = HashUtils.sha1(password)
        val downloadPictureError = repository.downloadPicture(userName, authorization)

        when (downloadPictureError) {
            DownloadPictureError.Ok -> repository.setUserLogin(userName)
            else -> repository.setUserLogin("")
        }

        return downloadPictureError
    }

    override suspend fun getPicture(): ByteArray {
        val picture = repository.getPicture()
        return decodeBase64(picture)
    }

    override suspend fun getUserLogin(): String {
        return repository.getUserLogin()
    }

    private fun decodeBase64(imageStr: String): ByteArray {
        var imageByteArray = ByteArray(0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            imageByteArray = Base64.getDecoder().decode(imageStr)
        } else {
            imageByteArray = android.util.Base64.decode(imageStr, android.util.Base64.DEFAULT);
        }
        return imageByteArray
    }
}