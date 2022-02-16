package com.example.testui.Resource

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klinikapp.DataSource.Layanan.Resource.*
import com.example.testui.Model.BeritaResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BeritaViewModel constructor(val beritaUseCase:BeritaUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val showNewsResult = MutableLiveData<Resource<BeritaResponse>>()

    fun showNews(q: String, from: String, sortBy: String, apiKey: String) {
        compositeDisposable.add(beritaUseCase.showNews(q, from, sortBy, apiKey)
            .doOnSubscribe { showNewsResult.setLoading() }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    showNewsResult.setSuccess(it)
                },
                {
                    showNewsResult.setError(ErrorMessageUtility.generateMessage(it))
                }
            )

        )
    }
}