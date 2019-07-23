package com.ciompa.cleverlance.repository

enum class DownloadPictureError {
    /**
     * Picture is downloaded, there is no problem
     */
    Ok,

    /**
     * No internet access
     */
    NoInternet,

    /**
     * incorrect user credential
     */
    Unauthorized,

    /**
     * Unknown error
     */
    UnknownError
}
