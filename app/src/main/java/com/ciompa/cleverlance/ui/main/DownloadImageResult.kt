package com.ciompa.cleverlance.ui.main

import com.ciompa.cleverlance.common.DownloadPictureError

data class DownloadImageResult(
    val error: DownloadPictureError,
    val message: Int,
    val picture: ByteArray
) {
    constructor() : this(DownloadPictureError.Ok, 0, ByteArray(0))
}
