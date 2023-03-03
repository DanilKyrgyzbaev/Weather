package com.art.studio.weather.domain.usecases

import com.art.studio.weather.data.repository.AccuWeatherRepositoryImpl
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(private val accuWeatherRepositoryImpl: AccuWeatherRepositoryImpl) {
    suspend fun invoke(
        locationKey: String,
        apiKey: String,
        language: String,
        isMetric: Boolean
    ) = accuWeatherRepositoryImpl.getDailyForecast(
        locationKey = locationKey,
        apiKey = apiKey,
        language = language,
        isMetric = isMetric
    )
}