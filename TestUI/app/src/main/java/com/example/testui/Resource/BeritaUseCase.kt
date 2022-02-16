package com.example.testui.Resource

import com.example.testui.Model.BeritaResponse
import io.reactivex.Single

class BeritaUseCase constructor(private val repository : BeritaRepository) {
    fun showNews(q: String, from: String, sortBy: String, apiKey: String): Single<BeritaResponse> {
        return repository.showNews(q, from, sortBy, apiKey)
    }

}
