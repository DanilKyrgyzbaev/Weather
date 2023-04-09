package com.art.studio.weather.data.model.locationModel

data class TimeZone(
    val Code: String,
    val Name: String,
    val GmtOffset: Int,
    val IsDaylightSaving: Boolean,
    val NextOffsetChange: Any?
)
