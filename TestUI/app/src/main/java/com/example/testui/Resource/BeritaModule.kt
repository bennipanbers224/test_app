package com.example.testui.Resource

import com.example.testui.API.API
import com.example.testui.BuildConfig
import com.example.testui.Network.Config.BASE_URL
import com.example.testui.Network.createNetworkClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadfeature

private val loadfeature by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule
    )
}

val viewModelModule = module {
    viewModel { BeritaViewModel(get()) }
}

val useCaseModule = module {
    factory { BeritaUseCase(repository = get()) }
}

val repositoryModule = module {
    single { BeritaRepositoryImpl(dataSource = get()) as BeritaRepository }
}

val dataSourceModule = module {
    single { BeritaDataSourceImpl(api = get()) as BeritaDataSource }
//    single { LayananCacheDataSourceImpl(preferencesHelper = get()) as LayananCacheDataSource }

}

val networkModule = module {
    single { createNetworkClient(BASE_URL, BuildConfig.DEBUG,androidApplication()).create(API::class.java) }
}