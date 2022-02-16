package com.example.testui.Network

import android.app.Application
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.FileInputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

fun  createNetworkClient(baseUrl: String, debug: Boolean = false, app: Application) =
    retrofitClient(baseUrl, httpClient(debug,app))

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

private fun httpClient(debug: Boolean, app: Application): OkHttpClient {
    val okhttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()


    val clientBuilder = okhttpBuilder
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor())
    if (debug) {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }

    return clientBuilder.build()

}

fun checkApkSha(app: Application): String {
    var hash = ""
    val apkPath: String = app.packageCodePath
    val msgDigest: MessageDigest
    try {
        msgDigest = MessageDigest.getInstance("SHA-1")
        val bytes = ByteArray(1024)
        var byteCount: Int
        val fis = FileInputStream(apkPath)
        while (fis.read(bytes).also { byteCount = it } > 0) {
            msgDigest.update(bytes, 0, byteCount)
        }
        val bi = BigInteger(1, msgDigest.digest())
        val sha = bi.toString(16)
        Log.i("checkApkSha", "apk sha=$sha")
        fis.close()
        hash = sha;
    } catch (e: Exception) {
        e.printStackTrace()
        hash = e.toString()
    }
    return hash
}

