package com.art.studio.weather.data.api.model.dailyForecast

data class Headline(
    val effectiveDate: String,
    val effectiveEpochDate: Long,
    val severity: Int,
    val text: String,
    val category: String,
    val endDate: String,
    val endEpochDate: Long,
    val mobileLink: String,
    val link: String
)
