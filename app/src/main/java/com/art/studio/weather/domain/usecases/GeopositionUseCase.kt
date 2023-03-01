package com.art.studio.weather.domain.usecases

import com.art.studio.weather.data.repository.AccuWeatherRepositoryImpl
import javax.inject.Inject

class GeopositionUseCase @Inject constructor( private val accuWeatherRepositoryImpl: AccuWeatherRepositoryImpl) {
    suspend fun invoke(apiKey: String, position: String, language: String) =
        accuWeatherRepositoryImpl.getGeoposition(
            apiKey = apiKey,
            position = position,
            language = language
        )
}