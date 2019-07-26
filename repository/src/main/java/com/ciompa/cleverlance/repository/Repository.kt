package com.ciompa.cleverlance.repository

import com.ciompa.cleverlance.common.DownloadPictureError

/**
 * Repository manage communication with web service and local storage
 */
interface Repository {

    /**
     * Download image for given user credential
     *
     * @param authorization user login password encrypted by SHA-1, put to header field Authorization
     * @param userName login user name
     * @return state of picture downloading
     */
    suspend fun downloadPicture(userName: String, authorization: String): DownloadPictureError

    /**
     * Return downloaded picture
     *
     * @return Base64-encoded downloaded picture, or empty string
     */
    suspend fun getPicture(): String

    /**
     * Return last username
     */
    suspend fun getUserLogin(): String

    /**
     * Set last used username
     */
    suspend fun setUserLogin(userName: String)

    /**
     * Set picture
     *
     * @param picture Base64-encoded picture, or empty string
     */
    suspend fun setPicture(picture: String)
}