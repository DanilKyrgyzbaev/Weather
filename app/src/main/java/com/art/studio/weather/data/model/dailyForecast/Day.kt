package com.art.studio.weather.data.model.dailyForecast

data class Day(
    val Icon: Int,
    val IconPhrase: String,
    val HasPrecipitation: Boolean,
    val PrecipitationType: String? = null,
    val PrecipitationIntensity: String? = null
)
