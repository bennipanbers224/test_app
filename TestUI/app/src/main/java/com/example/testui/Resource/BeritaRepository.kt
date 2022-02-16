package com.example.testui.Resource

import com.example.testui.Model.BeritaResponse
import io.reactivex.Single

interface BeritaRepository {
    fun showNews(q: String, from: String, sortBy: String, apiKey: String): Single<BeritaResponse>

}
