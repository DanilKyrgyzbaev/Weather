package com.art.studio.weather.data.api

import com.art.studio.weather.data.model.dailyForecast.DailyForecast
import com.art.studio.weather.data.model.dailyForecast.WeatherForecast
import com.art.studio.weather.data.model.hourlyForecastModel.HourlyForecast
import com.art.studio.weather.data.model.locationModel.Location
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccuWeatherApi {
    @GET("forecasts/v1/hourly/1hour/{locationKey}")
    suspend fun getHourlyForecast(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String,
        @Query("language") language: String,
        @Query("details") details: Boolean,
        @Query("metric") metric: Boolean
    ): Response<List<com.art.studio.weather.data.model.hourlyForecastModel.HourlyForecast>>

    @GET("/locations/v1/cities/geoposition/search")
    suspend fun searchCityByPosition(
        @Query("apikey") apiKey: String,
        @Query("q") position: String,
        @Query("language") language: String
    ): Response<com.art.studio.weather.data.model.locationModel.Location>


    @GET("forecasts/v1/daily/5day/{locationKey}")
    suspend fun getWeatherForecast(
        @Path("locationKey") locationKey: String,
        @Query("apikey") apiKey: String,
        @Query("language") language: String,
        @Query("metric") isMetric: Boolean
    ): Response<com.art.studio.weather.data.model.dailyForecast.WeatherForecast>
}