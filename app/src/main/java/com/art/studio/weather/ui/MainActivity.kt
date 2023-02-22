package com.art.studio.weather.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.art.studio.weather.databinding.ActivityMainBinding
import com.art.studio.weather.utils.ResultStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getAllWeather()
        getAllWeather()
    }

    fun getAllWeather(){

        viewModel.getAllWeatherResponse.observe(this){
            when(it){
                is ResultStatus.Loading -> {
                    Toast.makeText(this@MainActivity, "Синхронизация данных...", Toast.LENGTH_SHORT).show()
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
