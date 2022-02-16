package com.example.testui.API

import com.example.testui.Model.BeritaResponse
import io.reactivex.Single
import retrofit2.http.*

interface API {
    @GET("v2/everything")
    fun showNews(@Query("q")q: String,
                 @Query("from")from: String,
                 @Query("sortBy")sortBy: String,
                 @Query("apiKey")apiKey: String): Single<BeritaResponse>

}