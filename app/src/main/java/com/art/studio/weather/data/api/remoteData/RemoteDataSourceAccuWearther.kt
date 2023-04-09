package com.art.studio.weather.data.api.remoteData

import com.art.studio.weather.data.api.AccuWeatherApi
import javax.inject.Inject

class RemoteDataSourceAccuWearther @Inject constructor(private val accuWeatherApi: AccuWeatherApi) {
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