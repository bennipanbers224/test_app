package com.example.klinikapp.DataSource

import androidx.annotation.Keep

@Keep
data class BaseResponse<T> (
    var success: Boolean,
    var data: T,
    val message: String?=""
)