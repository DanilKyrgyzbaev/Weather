package com.art.studio.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.art.studio.weather.BuildConfig
import com.art.studio.weather.data.api.model.HourlyForecast
import com.art.studio.weather.domain.usecases.GetAllWeatherUseCase
import com.art.studio.weather.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllWeatherUseCase: GetAllWeatherUseCase
): ViewModel(){
    private val _getAllWeatherResponse = MutableLiveData<ResultStatus<List<HourlyForecast>>>()
    val getAllWeatherResponse: LiveData<ResultStatus<List<HourlyForecast>>>
    get() = _getAllWeatherResponse

    init {
        getAllWeather()
    }
    fun getAllWeather() {
        val locationKey = "222844"
        val apiKey = BuildConfig.API_KEY
        val language = "en"
        val details = true
        val metric = true
        viewModelScope.launch {
            getAllWeatherUseCase.invoke(
                locationKey = locationKey,
                apiKey = apiKey,
                language = language,
                details = details,
                metric = metric
            ).let {
                _getAllWeatherResponse.value = it
            }
        }
    }
}