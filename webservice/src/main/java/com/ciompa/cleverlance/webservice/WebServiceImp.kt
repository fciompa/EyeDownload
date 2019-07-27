package com.ciompa.cleverlance.webservice

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebServiceImp: WebService {

    private val WEB_SERVICE_URL = "https://mobility.cleverlance.com/"
    private val MEDIA_TYPE = "application/x-www-form-urlencoded"
    private val USER_NAME_REQUEST_BODY = "username="

    private val webServiceApi: WebServiceApi

    init {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        webServiceApi = Retrofit.Builder()
            .baseUrl(WEB_SERVICE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(WebServiceApi::class.java)
    }

    override suspend fun downloadImage(userName: String, authorization: String): DownloadImageResponse {

        val mediaType = MEDIA_TYPE.toMediaTypeOrNull()
        val body = (USER_NAME_REQUEST_BODY + userName).toRequestBody(mediaType)

        val response = webServiceApi.downloadImage(authorization, body)

        return DownloadImageResponse(response.body()?.image, response.code(), response.message())
    }
}