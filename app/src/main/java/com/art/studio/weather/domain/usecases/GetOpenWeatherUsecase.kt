package com.art.studio.weather.domain.usecases

import com.art.studio.weather.data.repository.AccuWeatherRepositoryImpl
import com.art.studio.weather.data.repository.OpenWeatherRepositoryImpl
import javax.inject.Inject

class GetOpenWeatherUsecase @Inject constructor(private val openWeatherRepositoryImpl: OpenWeatherRepositoryImpl) {
    suspend fun invoke(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
        language: String
    ) = openWeatherRepositoryImpl.getOpenWeather(
        latitude = latitude,
        longitude = longitude,
        apiKey = apiKey,
        units = units,
        language = language
    )
}