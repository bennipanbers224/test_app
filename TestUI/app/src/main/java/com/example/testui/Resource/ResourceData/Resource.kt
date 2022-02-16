package com.example.klinikapp.DataSource.Layanan.Resource


data class Resource<out T> constructor(
        val state: ResourceState?,
        val data: T? = null,
        val message: String? = ""
)