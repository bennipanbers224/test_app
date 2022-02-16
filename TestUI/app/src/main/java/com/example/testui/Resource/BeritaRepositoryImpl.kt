package com.example.testui.Resource

import com.example.testui.Model.BeritaResponse
import io.reactivex.Single

class BeritaRepositoryImpl constructor(private val dataSource : BeritaDataSource):BeritaRepository {
    override fun showNews(
        q: String,
        from: String,
        sortBy: String,
        apiKey: String
    ): Single<BeritaResponse> {
        return dataSource.showNews(q, from, sortBy, apiKey)
    }
}