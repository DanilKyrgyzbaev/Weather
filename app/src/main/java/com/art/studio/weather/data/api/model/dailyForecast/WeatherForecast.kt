package com.art.studio.weather.data.api.model.dailyForecast

data class WeatherForecast(
    val Headline: Headline,
    var DailyForecasts: List<DailyForecast>
)
