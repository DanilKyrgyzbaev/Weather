package com.art.studio.weather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.art.studio.weather.BuildConfig
import com.art.studio.weather.data.api.model.hourlyForecastModel.HourlyForecast
import com.art.studio.weather.data.api.model.locationModel.Location
import com.art.studio.weather.domain.usecases.GeopositionUseCase
import com.art.studio.weather.domain.usecases.GetAllWeatherUseCase
import com.art.studio.weather.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllWeatherUseCase: GetAllWeatherUseCase,
    private val geopositionUseCase: GeopositionUseCase,
    private val locationManager: LocationManager
): ViewModel(), LocationListener {
    private val _getAllWeatherResponse = MutableLiveData<ResultStatus<List<HourlyForecast>>>()
    private val _getGeopositionResponse = MutableLiveData<ResultStatus<Location>>()
    val getAllWeatherResponse: LiveData<ResultStatus<List<HourlyForecast>>> get() = _getAllWeatherResponse
    val getGeopositionResponse: LiveData<ResultStatus<Location>> get() = _getGeopositionResponse
    private val _latlon = MutableLiveData<String>()
    val latlon: LiveData<String> get() = _latlon

    fun getAllWeather(
        locationKey: String,
        apiKey: String,
        language: String,
        details: Boolean,
        metric: Boolean
    ) {
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

    fun getGeoposition(apiKey: String, position: String, language: String){
        viewModelScope.launch {
            geopositionUseCase.invoke(
                apiKey = apiKey,
                position = position,
                language = language
            ).let {
                _getGeopositionResponse.value = it
            }
        }

    }

    fun checkSelfPermission(mainActivity: MainActivity){
        if (ActivityCompat.checkSelfPermission(mainActivity.application, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(mainActivity.application, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(mainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        } else {
            // Start location updates
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 0f, this)
        }
    }
    override fun onLocationChanged(locations: android.location.Location) {
        locationManager.removeUpdates(this)
        val latlon = "${locations.latitude},${locations.longitude}"
        _latlon.value = latlon
        Log.e("LatLon",latlon)
    }
}