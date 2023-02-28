package com.art.studio.weather.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.art.studio.weather.BuildConfig
import com.art.studio.weather.data.api.model.dailyForecast.DailyForecast
import com.art.studio.weather.databinding.ActivityMainBinding
import com.art.studio.weather.ui.adapter.DailyForecastAdapter
import com.art.studio.weather.utils.ResultStatus
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: DailyForecastAdapter
    private val apikey = BuildConfig.API_KEY
    private var locationKey: String? = null
    private val URL = "https://apidev.accuweather.com/developers/Media/Default/WeatherIcons/"
    private var icon = ""
    private var data: String? = null
    private var language = "en"

    private lateinit var lSwipeDetector: GestureDetectorCompat

    companion object {
        private const val SWIPE_MIN_DISTANCE = 130
        private const val SWIPE_MAX_DISTANCE = 300
        private const val SWIPE_MIN_VELOCITY = 200
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.checkSelfPermission(this@MainActivity)
        lSwipeDetector = GestureDetectorCompat(this, MyGestureListener())
        binding.mainLayout.setOnTouchListener { _, event -> lSwipeDetector.onTouchEvent(event) }

        binding.recyclerview.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        adapter = DailyForecastAdapter(emptyList())
        binding.recyclerview.adapter = adapter

        getLongitudeLatitude()

        binding.kg.setOnClickListener{
            language = "kg"
            Toast.makeText(this@MainActivity, "Это не доступно...", Toast.LENGTH_SHORT).show()
        }
        binding.us.setOnClickListener {
            language = "us"
            getLongitudeLatitude()
        }
        binding.ru.setOnClickListener {
            language = "ru"
            getLongitudeLatitude()
        }
    }
    fun getLongitudeLatitude(){
        viewModel.latlon.observe(this){
            if (it.isNotEmpty()){
                viewModel.getGeoposition(apikey,it.toString(),language)
                getGeoposition()
            }else{
                Log.e("Longitude&Latitude","Пустой")
            }
        }
    }

    fun getGeoposition(){
        viewModel.getGeopositionResponse.observe(this){
            when(it){
                is ResultStatus.Loading -> {
                    Toast.makeText(this@MainActivity, "Get Geoposition...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Success -> {
                    binding.localityTv.text = it.data?.TimeZone?.Name
                    locationKey = it.data?.Key
                    Log.e("TAG", "Sync Result Status Success: ${it.data?.TimeZone?.Name} ")
                    viewModel.getAllWeather(locationKey.toString(),apikey,language,true,true)
                    viewModel.getDailyForecasts(locationKey.toString(),apikey,language,true)
                    getAllWeather()
                    getDailyForecast()
                }
                is ResultStatus.Error -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun getAllWeather(){
        viewModel.getAllWeatherResponse.observe(this){
            when(it){
                is ResultStatus.Loading -> {
                    Toast.makeText(this@MainActivity, "Get  AllWeather...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Success -> {
                    binding.tempTv.text = it.data?.get(0)?.Temperature?.Value.toString()
                    binding.windGustsGetTv.text = "${it.data?.get(0)?.WindGust?.Speed?.Value.toString()} km/h"
                    binding.windGetTv.text = "${it.data?.get(0)?.Wind?.Speed?.Value.toString()} km/h"
                    binding.phraseTv.text = it.data?.get(0)?.IconPhrase
                    binding.realFeelGetTv.text = "${it.data?.get(0)?.RealFeelTemperature?.Value.toString()}°"
                    data = it.data?.get(0)?.DateTime
                    icon = it.data?.get(0)?.WeatherIcon.toString()
                    when (icon.toInt()) {
                        in 1..9 -> icon = "0$icon-s.png"
                        in 10..Int.MAX_VALUE -> icon = "$icon-s.png"
                        else -> println("некорректный URL")
                    }
                    dataFormat()
                    getIcon()
                    Log.e("TAG", "Sync Result Status Success: ${it.data} ")
                    Toast.makeText(this@MainActivity, "Status Success...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Error -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
            }
        }
    }

    fun getDailyForecast(){
        viewModel.dailyForecasts.observe(this) { newForecasts ->
            if (newForecasts != null) {
               // adapter.dailyForecasts = viewModel.dailyForecasts.value.dailyForecasts
                Log.e("Olololololololololo","${adapter.dailyForecasts}")
                adapter.notifyDataSetChanged()
            } else{
                Log.e("Olololololololololo","${viewModel.dailyForecasts.value}")

            }
        }
    }

    fun dataFormat(){
        val inputDateFormat = DateTimeFormatter.ISO_DATE_TIME
        val outputDateFormat = DateTimeFormatter.ofPattern("EEE, d. MMM", Locale.getDefault())

        val inputDate = LocalDateTime.parse(data, inputDateFormat)
        val outputDate = inputDate.format(outputDateFormat)
        binding.dateTv.text = outputDate
    }

    fun getIcon(){
        Glide
            .with(this)
            .load("$URL$icon")
            .centerCrop()
            .into(binding.cludyIv);
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }
        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            if (Math.abs(e1.y - e2.y) > SWIPE_MAX_DISTANCE) return false
            if (e2.x - e1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_MIN_VELOCITY) {
                getLongitudeLatitude()
                Log.e("MotionEvent","MotionEvent")
            }
            return false
        }
    }
}

