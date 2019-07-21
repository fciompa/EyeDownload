package com.ciompa.cleverlance.webservice

/**
 * Response from download image web service endpoint
 */
data class DownloadImageResponse(
    /**
     * Image encoded by Base-64
     */
    val imageEncoded: String?,

    /**
     * Http response status code
     */
    val code: Int,

    /**
     * Http response message
     */
    val message: String
)