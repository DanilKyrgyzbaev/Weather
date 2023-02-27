package com.art.studio.weather.data.api.model.dailyForecast

data class Night(
    val icon: Int,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val precipitationType: String? = null,
    val precipitationIntensity: String? = null
)
