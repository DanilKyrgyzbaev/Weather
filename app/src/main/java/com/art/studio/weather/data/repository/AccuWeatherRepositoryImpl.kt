package com.art.studio.weather.data.repository

import com.art.studio.weather.data.api.RemoteDataSource
import com.art.studio.weather.data.api.model.hourlyForecastModel.HourlyForecast
import com.art.studio.weather.data.api.model.locationModel.Location
import com.art.studio.weather.utils.BaseApiResponse
import com.art.studio.weather.utils.ResultStatus
import javax.inject.Inject

class AccuWeatherRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): BaseApiResponse() {
    suspend fun getAllHourlyForecast(
        locationKey: String,
        apiKey: String,
        language: String,
        details: Boolean,
        metric: Boolean
    ): ResultStatus<List<HourlyForecast>> {
        return saveApiCall {
            remoteDataSource.getAllHourlyForecast(
                locationKey = locationKey,
                apiKey = apiKey,
                language = language,
                details = details,
                metric = metric
            )
        }
    }

    suspend fun getGeoposition(apiKey: String, position: String, language: String): ResultStatus<Location>{
        return saveApiCall {
            remoteDataSource.getGeoposition(apiKey = apiKey,position = position,language = language)
        }
    }
}