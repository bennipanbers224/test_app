package com.example.klinikapp.DataSource.Layanan.Resource

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException
import java.io.IOException
import java.util.*

object ErrorMessageUtility {
    private const val DEFAULT_ERROR_MESSAGE  = "Koneksi Internet Bermasalah!"

    private const val STATUS_SERVER_ERROR_MESSAGE = "Tidak bisa terhubung ke server"
    private val STATUS_UNAUTHORIZED = 401
    private val STATUS_NOT_FOUND = 404
    private val STATUS_BAD_REQUEST = 400
    private val STATUS_FORBIDDEN = 403
    private val STATUS_SERVER_ERROR = 500
    private val STATUS_NOT_IMPLEMENTED = 501
    private val STATUS_BAD_GATEWAY = 502
    private val STATUS_SERVICE_UNAVAILABLE = 503

    fun generateMessage(throwable: Throwable): String {
        return  when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    STATUS_SERVER_ERROR -> STATUS_SERVER_ERROR_MESSAGE
                    STATUS_UNAUTHORIZED->{
                        generalErrorMessage(throwable)
                    }
                    else -> DEFAULT_ERROR_MESSAGE
                }
            }
            else -> DEFAULT_ERROR_MESSAGE
        }
    }

    private fun generalErrorMessage(e: Throwable):String {
        val body = Objects.requireNonNull((e as HttpException).response()).errorBody()
        val adapter: TypeAdapter<ErrorResponse> = Gson().getAdapter(
            ErrorResponse::class.java
        )
        return try {
            val errorParser: ErrorResponse = adapter.fromJson(
                body?.string()
            )
            errorParser.data
        } catch (exc: IOException) {
            "Auth Not Found"
        }
    }
}