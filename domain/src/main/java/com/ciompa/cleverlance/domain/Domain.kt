package com.ciompa.cleverlance.domain

import com.ciompa.cleverlance.common.DownloadPictureError

interface Domain {
    /**
     * Download image for given user credential
     *
     * @param authorization user password
     * @param userName login user name
     * @return state of picture downloading
     */
    suspend fun downloadPicture(userName: String, authorization: String): DownloadPictureError

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

}