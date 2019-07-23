package com.ciompa.cleverlance.repository

import androidx.annotation.VisibleForTesting
import com.ciompa.cleverlance.storage.PropertyEntity
import com.ciompa.cleverlance.storage.Storage
import com.ciompa.cleverlance.webservice.WebService

class RepositoryImp(
    private val connectivityMonitor: com.ciompa.cleverlance.common.ConnectivityMonitor,
    private val webService: WebService,
    private val storage: Storage
) : Repository {

    override suspend fun downloadPicture(userName: String, authorization: String): DownloadPictureError {
        if (connectivityMonitor.isConnected()) {
            return download(userName, authorization)
        } else {
            return DownloadPictureError.NoInternet
        }
    }

    private suspend fun download(userName: String, authorization: String): DownloadPictureError {

        val response = webService.downloadImage(userName, authorization)

        return when (response.code) {
            200 -> setDownloadedPicture(response.imageEncoded)
            401 -> setUnauthorized()
            else -> setUnknownError()
        }
    }

    private suspend fun setDownloadedPicture(picture: String?): DownloadPictureError {
        setPicture(picture ?: "")
        return DownloadPictureError.Ok
    }

    private suspend fun setUnauthorized(): DownloadPictureError {
        setPicture("")
        return DownloadPictureError.Unauthorized
    }

    private suspend fun setUnknownError(): DownloadPictureError {
        setPicture("")
        return DownloadPictureError.UnknownError
    }

    override suspend fun getPicture(): String {
        val userLogin = storage.dao.property(PICTURE)
        return userLogin?.value ?: ""
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun setPicture(picture: String) {
        var pictureProperty = storage.dao.property(PICTURE)

        if (pictureProperty == null) {
            pictureProperty = PropertyEntity(0, PICTURE, "")
        }

        pictureProperty.value = picture
        storage.dao.insertProperty(pictureProperty)
    }

    override suspend fun getUserLogin(): String {
        val userLogin = storage.dao.property(USER_LOGIN)
        return userLogin?.value ?: ""
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    suspend fun setUserLogin(userName: String) {
        var userLoggingProperty = storage.dao.property(USER_LOGIN)

        if (userLoggingProperty == null) {
            userLoggingProperty = PropertyEntity(0, USER_LOGIN, "")
        }

        userLoggingProperty.value = userName
        storage.dao.insertProperty(userLoggingProperty)
    }

    companion object {
        private const val USER_LOGIN = "UserLogin"
        private const val PICTURE = "Picture"

    }
}