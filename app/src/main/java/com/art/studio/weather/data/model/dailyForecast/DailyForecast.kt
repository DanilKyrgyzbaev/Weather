package com.art.studio.weather.data.model.dailyForecast

data class DailyForecast(
    val Date: String,
    val EpochDate: Long,
    val Temperature: com.art.studio.weather.data.model.dailyForecast.Temperature,
    val Day: com.art.studio.weather.data.model.dailyForecast.Day,
    val Night: com.art.studio.weather.data.model.dailyForecast.Night,
    val Sources: List<String>,
    val MobileLink: String,
    val Link: String
)
