package com.art.studio.weather.data.api

import com.art.studio.weather.data.model.openWearher.OpenWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWearherApi {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Response<OpenWeatherResponse>
}