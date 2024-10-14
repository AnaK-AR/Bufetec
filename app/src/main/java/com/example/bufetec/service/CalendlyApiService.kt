package com.example.bufetec.service

import com.example.bufetec.model.CalendlyEventsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CalendlyApiService {

    @GET("scheduled_events")
    suspend fun getScheduledEvents(
        @Header("Authorization") authHeader: String,
        @Query("user") userUri: String,
        @Query("min_start_time") minStartTime: String,
        @Query("max_start_time") maxStartTime: String
    ): CalendlyEventsResponse
}