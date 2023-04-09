package com.art.studio.weather.data.model.dailyForecast

data class Headline(
    val EffectiveDate: String,
    val EffectiveEpochDate: Long,
    val Severity: Int,
    val Text: String,
    val Category: String,
    val EndDate: String,
    val EndEpochDate: Long,
    val MobileLink: String,
    val Link: String
)
