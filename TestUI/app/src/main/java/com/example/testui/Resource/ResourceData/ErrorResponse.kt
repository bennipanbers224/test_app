package com.example.klinikapp.DataSource.Layanan.Resource

import androidx.annotation.Keep

@Keep
data class ErrorResponse(
    var result: String,
    var data: String,
    val message: String?=""
)