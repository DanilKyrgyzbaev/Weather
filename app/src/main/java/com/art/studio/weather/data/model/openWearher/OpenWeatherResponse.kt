package com.art.studio.weather.data.model.openWearher

import com.google.gson.annotations.SerializedName

data class OpenWeatherResponse(
    @SerializedName("main")
    val main: com.art.studio.weather.data.model.openWearher.OpenWeatherResponse.Main,
    @SerializedName("weather")
    val weather: List<com.art.studio.weather.data.model.openWearher.OpenWeatherResponse.Weather>,
    @SerializedName("name")
    val name: String
) {
    data class Main(
        @SerializedName("temp")
        val temp: Double
    )

    data class Weather(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String
    )
}
