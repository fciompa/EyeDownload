package com.ciompa.cleverlance.domain

import com.ciompa.cleverlance.common.DownloadPictureError
import com.ciompa.cleverlance.common.HashUtils
import com.ciompa.cleverlance.common.inject
import com.ciompa.cleverlance.repository.Repository

class DomainImp() : Domain {

    private val repository: Repository by inject()

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
        return MyBase64Decoder(picture).decode()
    }

    override suspend fun getUserLogin(): String {
        return repository.getUserLogin()
    }

    override fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length > 3;
    }

}