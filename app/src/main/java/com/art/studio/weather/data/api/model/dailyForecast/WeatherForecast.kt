package com.art.studio.weather.data.api.model.dailyForecast

data class WeatherForecast(
    val headline: Headline,
    val dailyForecasts: List<DailyForecast>
)
