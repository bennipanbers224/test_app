package com.example.testui.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BeritaResponse(
    @SerializedName("status")
    var status:String,
    @SerializedName("totalResults")
    var totalResults:Int,
    @SerializedName("articles")
    var articles:ArrayList<Articles>
):Parcelable

@Parcelize
data class Articles(
    @SerializedName("source")
    var source:Source,
    @SerializedName("author")
    var author:String,
    @SerializedName("title")
    var title:String,
    @SerializedName("description")
    var desc:String,
    @SerializedName("url")
    var url:String,
    @SerializedName("urlToImage")
    var urlToImage:String,
    @SerializedName("publishedAt")
    var publishedAt:String,
    @SerializedName("content")
    var content:String,
):Parcelable

@Parcelize
data class Source(
    @SerializedName("id")
    var id:String,
    @SerializedName("name")
    var name:String,
):Parcelable