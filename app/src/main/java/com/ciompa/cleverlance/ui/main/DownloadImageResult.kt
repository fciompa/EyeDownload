package com.ciompa.cleverlance.ui.main

import com.ciompa.cleverlance.common.DownloadPictureError

data class DownloadImageResult(
    val error: DownloadPictureError,
    val message: Int
) {
    constructor() : this(DownloadPictureError.Ok, 0)
}
