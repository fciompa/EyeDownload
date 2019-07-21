package com.ciompa.cleverlance.webservice

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface WebServiceApi {

    /**
     * Download image for given user credential
     *
     *  @param authorization user login password encrypted by SHA-1, put to header field Authorization
     *  @param requestBody request body with login user name
     */
    @POST("download/bootcamp/image.php")
    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Host: mobility.cleverlance.com"
    )
    suspend fun downloadImage(
        @Header("Authorization") authorization: String,
        @Body requestBody: RequestBody
    ): Response<Image>

}