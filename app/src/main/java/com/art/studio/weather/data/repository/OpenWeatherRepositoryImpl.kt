package com.art.studio.weather.data.repository

import com.art.studio.weather.data.api.remoteData.RemoteDataSourceOpenWearther
import com.art.studio.weather.utils.BaseApiResponse
import com.art.studio.weather.utils.ResultStatus
import javax.inject.Inject

class OpenWeatherRepositoryImpl @Inject constructor(private val remoteDataSourceOpenWearther: RemoteDataSourceOpenWearther): BaseApiResponse() {

    suspend fun getOpenWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
        language: String
    ): ResultStatus<com.art.studio.weather.data.model.openWearher.OpenWeatherResponse> {
        return saveApiCall {
            remoteDataSourceOpenWearther.getOpenWeather(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey,
                units = units,
                language = language
            )
        }
    }
}