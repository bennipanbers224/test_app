package com.example.klinikapp.DataSource.Layanan.Resource

sealed class ResourceState {
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}