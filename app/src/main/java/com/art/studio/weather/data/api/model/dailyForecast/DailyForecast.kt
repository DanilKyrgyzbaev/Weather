package com.art.studio.weather.data.api.model.dailyForecast

data class DailyForecast(
    val Date: String,
    val EpochDate: Long,
    val Temperature: Temperature,
    val Day: Day,
    val Night: Night,
    val Sources: List<String>,
    val MobileLink: String,
    val Link: String
)
