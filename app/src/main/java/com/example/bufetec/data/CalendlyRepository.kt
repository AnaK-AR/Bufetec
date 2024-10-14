package com.example.bufetec.data

import com.example.bufetec.BuildConfig
import com.example.bufetec.model.TimeSlot
import com.example.bufetec.network.CalendlyApiClient
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.ZoneId

class CalendlyRepository {
    private val apiService = CalendlyApiClient.service
    private val authToken = "Bearer ${BuildConfig.CALENDLY_API_TOKEN}"
    private val userUri = BuildConfig.CALENDLY_USER_URI

    suspend fun getAvailableTimeSlots(selectedDate: LocalDate): List<TimeSlot> {
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

        val zoneId = ZoneId.systemDefault()

        val minStartTime = selectedDate.atStartOfDay(zoneId).format(formatter)

        val maxStartTime = selectedDate.plusDays(1).atStartOfDay(zoneId).format(formatter)

        val response = apiService.getScheduledEvents(
            authHeader = authToken,
            userUri = userUri,
            minStartTime = minStartTime,
            maxStartTime = maxStartTime
        )

        return response.collection.map { event ->
            TimeSlot(
                id = event.uri,
                name = event.name,
                startTime = ZonedDateTime.parse(event.startTime),
                endTime = ZonedDateTime.parse(event.endTime)
            )
        }
    }
}