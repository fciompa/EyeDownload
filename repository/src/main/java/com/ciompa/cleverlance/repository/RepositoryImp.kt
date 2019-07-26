package com.ciompa.cleverlance.repository

import com.ciompa.cleverlance.common.ConnectivityMonitor
import com.ciompa.cleverlance.common.DownloadPictureError
import com.ciompa.cleverlance.common.inject
import com.ciompa.cleverlance.storage.PictureEntity
import com.ciompa.cleverlance.storage.PropertyEntity
import com.ciompa.cleverlance.storage.Storage
import com.ciompa.cleverlance.webservice.WebService

class RepositoryImp(
    private val connectivityMonitor: ConnectivityMonitor
) : Repository {

    private val webService: WebService by inject()
    private val storage: Storage by inject()

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
        val pictureEntity = storage.dao.picture()
        return pictureEntity?.value ?: ""
    }

    override suspend fun setPicture(picture: String) {
        var pictureEntity = storage.dao.picture()

        if (pictureEntity == null) {
            pictureEntity = PictureEntity(0, "")
        }

        pictureEntity.value = picture
        storage.dao.insertPicture(pictureEntity)
    }

    override suspend fun getUserLogin(): String {
        val userLogin = storage.dao.property(USER_LOGIN)
        return userLogin?.value ?: ""
    }

    override suspend fun setUserLogin(userName: String) {
        var userLoggingProperty = storage.dao.property(USER_LOGIN)

        if (userLoggingProperty == null) {
            userLoggingProperty = PropertyEntity(0, USER_LOGIN, "")
        }

        userLoggingProperty.value = userName
        storage.dao.insertProperty(userLoggingProperty)
    }

    companion object {
        private const val USER_LOGIN = "UserLogin"

    }
}