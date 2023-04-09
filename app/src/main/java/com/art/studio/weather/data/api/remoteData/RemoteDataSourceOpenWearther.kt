package com.art.studio.weather.data.api.remoteData

import com.art.studio.weather.data.api.OpenWearherApi
import javax.inject.Inject

class RemoteDataSourceOpenWearther @Inject constructor(private val openWearherApi: OpenWearherApi) {

    suspend fun getOpenWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
        language: String
    ) = openWearherApi.getWeatherData(latitude, longitude, apiKey, units, language)
}