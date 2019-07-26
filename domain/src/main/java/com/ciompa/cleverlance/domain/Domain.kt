package com.ciompa.cleverlance.domain

import com.ciompa.cleverlance.common.DownloadPictureError

interface Domain {
    /**
     * Download image for given user credential
     *
     * @param password user password
     * @param userName login user name
     * @return state of picture downloading
     */
    suspend fun downloadPicture(userName: String, password: String): DownloadPictureError

    /**
     * Return downloaded picture
     *
     * @return Base64-encoded downloaded picture, or empty string
     */
    suspend fun getPicture(): ByteArray

    /**
     * Return last username
     */
    suspend fun getUserLogin(): String

    /**
     * username validation check
     *
     * @return true - user name is valid, false - user name is not valid, user name is empty
     */
    fun isUserNameValid(username: String): Boolean

    /**
     * password validation check
     *
     * @return true - password is valid, false - password is not valid, length is less then 4
     */
    fun isPasswordValid(password: String): Boolean
}