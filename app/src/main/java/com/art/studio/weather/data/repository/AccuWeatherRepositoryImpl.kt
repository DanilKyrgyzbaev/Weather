package com.art.studio.weather.data.repository

import com.art.studio.weather.data.api.remoteData.RemoteDataSourceAccuWearther
import com.art.studio.weather.utils.BaseApiResponse
import com.art.studio.weather.utils.ResultStatus
import javax.inject.Inject

class AccuWeatherRepositoryImpl @Inject constructor(private val remoteDataSourceAccuWearther: RemoteDataSourceAccuWearther): BaseApiResponse() {
    suspend fun getAllHourlyForecast(
        locationKey: String,
        apiKey: String,
        language: String,
        details: Boolean,
        metric: Boolean
    ): ResultStatus<List<com.art.studio.weather.data.model.hourlyForecastModel.HourlyForecast>> {
        return saveApiCall {
            remoteDataSourceAccuWearther.getAllHourlyForecast(
                locationKey = locationKey,
                apiKey = apiKey,
                language = language,
                details = details,
                metric = metric
            )
        }
    }
    suspend fun getGeoposition(
        apiKey: String,
        position: String,
        language: String
    ): ResultStatus<com.art.studio.weather.data.model.locationModel.Location> {
        return saveApiCall {
            remoteDataSourceAccuWearther.getGeoposition(
                apiKey = apiKey,
                position = position,
                language = language
            )
        }
    }

    suspend fun getDailyForecast(
        locationKey: String,
        apiKey: String,
        language: String,
        isMetric: Boolean
    ): ResultStatus<com.art.studio.weather.data.model.dailyForecast.WeatherForecast> {
        return saveApiCall {
            remoteDataSourceAccuWearther.getDailyForecast(
                locationKey = locationKey,
                apiKey = apiKey,
                language = language,
                isMetric = isMetric
            )
        }
    }

}