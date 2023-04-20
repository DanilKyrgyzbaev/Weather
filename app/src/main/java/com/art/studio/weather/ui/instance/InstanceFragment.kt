package com.art.studio.weather.ui.instance

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.art.studio.weather.BuildConfig
import com.art.studio.weather.R
import com.art.studio.weather.databinding.FragmentInstanceBinding
import com.art.studio.weather.ui.MainActivity
import com.art.studio.weather.ui.MainViewModel
import com.art.studio.weather.ui.adapter.DailyForecastAdapter
import com.art.studio.weather.utils.ResultStatus
import com.bumptech.glide.Glide
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class InstanceFragment : Fragment() {
    private var _binding: FragmentInstanceBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: DailyForecastAdapter
    private val apikey = BuildConfig.API_KEY_AccuWeather
    private var locationKey: String? = null
    private val URL = "https://apidev.accuweather.com/developers/Media/Default/WeatherIcons/"
    private var icon = ""
    private var data: String? = null
    private var language = "en"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstanceBinding.inflate(inflater, container,false)
        val view = binding.root
        viewModel.checkSelfPermission(requireActivity())

        getLongitudeLatitude()
        adapter = DailyForecastAdapter(ArrayList(),requireContext())
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return view
    }


    fun getLongitudeLatitude(){
        viewModel.latlon.observe(requireActivity()){
            if (it.isNotEmpty()){
                viewModel.getGeoposition(apikey,it.toString(),language)
                getGeoposition()
            }else{
                Log.e("Longitude&Latitude","Пустой")
            }
        }
    }

    fun getGeoposition(){
        viewModel.getGeopositionResponse.observe(requireActivity()){
            when(it){
                is ResultStatus.Loading -> {
                    Toast.makeText(context, "Get Geoposition...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Success -> {
                    binding.localityTv.text = it.data?.TimeZone?.Name
                    locationKey = it.data?.Key
                    Log.e("TAG", "Sync Result Status Success: ${it.data?.TimeZone?.Name} ")
                    viewModel.getAllWeather(locationKey.toString(),apikey,language,true,true)
                    getAllWeather()
                    viewModel.getDailyForecasts(locationKey.toString(),apikey,language,true)
                    getDailyForecast()
                }
                is ResultStatus.Error -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
                else -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun getAllWeather(){
        viewModel.getAllWeatherResponse.observe(requireActivity()){
            when(it){
                is ResultStatus.Loading -> {
                    Toast.makeText(context, "Get  AllWeather...", Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(context, "Status Success...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Error -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
                else -> {
                    Log.e("TAG", "Sync Result Status Error: ${it.message} ")
                }
            }
        }
    }

    fun getDailyForecast() {
        viewModel.dailyForecasts.observe(requireActivity()) { newForecasts ->
            when (newForecasts) {
                is ResultStatus.Loading -> {
                    Toast.makeText(context, "Get  AllWeather...", Toast.LENGTH_SHORT).show()
                }
                is ResultStatus.Success -> {
                    val dailyForecasts = newForecasts.data?.DailyForecasts ?: emptyList()
                    adapter.updateData(dailyForecasts)
                }
                is ResultStatus.Error -> {
                    Log.e("Error AllWeather Daily", "Sync Result Status Error: ${newForecasts.message} ")
                }
                else -> {
                    Log.e("Error AllWeather Daily", "Sync Result Status Error: ${newForecasts.message} ")

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
            .into(binding.cludyIv)
    }

}