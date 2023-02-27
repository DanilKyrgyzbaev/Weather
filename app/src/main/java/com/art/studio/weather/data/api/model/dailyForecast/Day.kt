package com.art.studio.weather.data.api.model.dailyForecast

data class Day(
    val icon: Int,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val precipitationType: String? = null,
    val precipitationIntensity: String? = null
)
