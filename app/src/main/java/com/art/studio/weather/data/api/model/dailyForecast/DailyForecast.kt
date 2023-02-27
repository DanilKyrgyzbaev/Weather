package com.art.studio.weather.data.api.model.dailyForecast

data class DailyForecast(
    val date: String,
    val epochDate: Long,
    val temperature: Temperature,
    val day: Day,
    val night: Night,
    val sources: List<String>,
    val mobileLink: String,
    val link: String
)
