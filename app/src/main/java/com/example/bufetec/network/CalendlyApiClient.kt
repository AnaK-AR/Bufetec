package com.example.bufetec.network

import com.example.bufetec.service.CalendlyApiService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CalendlyApiClient {
    private const val BASE_URL = "https://api.calendly.com/"

    private val moshi = Moshi.Builder()
        .build()

    private val client = OkHttpClient.Builder().build()

    val service: CalendlyApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        retrofit.create(CalendlyApiService::class.java)
    }
}