package com.art.studio.weather.domain.usecases

import com.art.studio.weather.data.repository.AccuWeatherRepositoryImpl
import javax.inject.Inject

class GetAllWeatherUseCase @Inject constructor(private val accuWeatherRepositoryImpl: AccuWeatherRepositoryImpl){
    suspend fun invoke(
        locationKey: String,
        apiKey: String,
        language: String,
        details: Boolean,
        metric: Boolean
    ) = accuWeatherRepositoryImpl.getAllHourlyForecast(
        locationKey = locationKey,
        apiKey = apiKey,
        language = language,
        details = details,
        metric = metric
    )
}