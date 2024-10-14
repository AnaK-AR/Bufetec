package com.example.bufetec.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
data class CalendlyEventsResponse(
    val collection: List<CalendlyEvent>
)

@JsonClass(generateAdapter = true)
data class CalendlyEvent(
    val uri: String,
    val name: String,
    @Json(name = "start_time") val startTime: String,
    @Json(name = "end_time") val endTime: String
)

data class TimeSlot(
    val id: String,
    val name: String,
    val startTime: ZonedDateTime,
    val endTime: ZonedDateTime
)