package com.art.studio.weather.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.art.studio.weather.BuildConfig
import com.art.studio.weather.databinding.ActivityMainBinding
import com.art.studio.weather.utils.ResultStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val apikey = BuildConfig.API_KEY
    private var longitude: String? = null
    private var latitude: String? = null
    private var locationKey: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.checkSelfPermission(this@MainActivity)
        getLongitudeLatitude()
    }

    fun getLongitudeLatitude(){
        viewModel.longitude.observe(this){
            longitude = it.toString()
            Log.e("Longitude", "$it")

            viewModel.latitude.observe(this){
                latitude = it.toString()
                Log.e("Latitude", "$it")

                viewModel.getGeoposition(apikey,"$latitude,$longitude","en")
                viewModel.getAllWeather(locationKey.toString(),apikey,"en",true,true)
                getAllWeather()
            }
        }
        finish()
    }

    fun getAllWeather(){
        viewModel.getGeopositionResponse.observe(this){
            when(it){
                is ResultStatus.Loading -> {
                    Toast.makeText(this@MainActivity, "Get Geoposition...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Success -> {
                    binding.localityTv.text = it.data?.TimeZone?.Name
                    locationKey = it.data?.Key
                    Log.e("TAG", "Sync Result Status Success: ${it.data?.TimeZone?.Name} ")
                }
                is ResultStatus.Error -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
            }
        }

        viewModel.getAllWeatherResponse.observe(this){
            when(it){
                is ResultStatus.Loading -> {
                    Toast.makeText(this@MainActivity, "Get  AllWeather...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Success -> {
                    binding.tempTv.text = it.data?.get(0)?.Temperature?.Value.toString()
                    Log.e("TAG", "Sync Result Status Success: ${it.data} ")
                    Toast.makeText(this@MainActivity, "Status Success...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Error -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
            }
        }
    }
}
