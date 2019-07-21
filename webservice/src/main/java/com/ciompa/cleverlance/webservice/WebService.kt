package com.ciompa.cleverlance.webservice

/**
 * Web service of the app
 */
interface WebService {
    /**
     * Download image for given user credential
     *
     * @param authorization user login password encrypted by SHA-1, put to header field Authorization
     * @param userName login user name
     */
    suspend fun downloadImage(userName: String, authorization: String): DownloadImageResponse
}