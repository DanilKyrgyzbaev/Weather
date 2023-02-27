package com.art.studio.weather.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val accuWeatherApi: AccuWeatherApi) {
    // получаем данные из Interface
    suspend fun getAllHourlyForecast(
        locationKey: String,
        apiKey: String,
        language: String,
        details: Boolean,
        metric: Boolean
    ) = accuWeatherApi.getHourlyForecast(locationKey, apiKey, language, details, metric)

    suspend fun getGeoposition(apiKey: String, position: String, language: String) =
        accuWeatherApi.searchCityByPosition(apiKey,position,language)

    suspend fun getDailyForecast(
        locationKey: String,
        apiKey: String,
        language: String,
        isMetric: Boolean
    ) = accuWeatherApi.getWeatherForecast(locationKey,apiKey,language,isMetric)
}