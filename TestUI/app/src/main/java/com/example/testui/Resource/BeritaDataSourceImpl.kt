package com.example.testui.Resource

import com.example.testui.API.API
import com.example.testui.Model.BeritaResponse
import io.reactivex.Single

class BeritaDataSourceImpl constructor(private val api: API):BeritaDataSource {
    override fun showNews(
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): Single<BeritaResponse> {
        return api.showNews(q, from, sortBy, apiKey)
    }
}