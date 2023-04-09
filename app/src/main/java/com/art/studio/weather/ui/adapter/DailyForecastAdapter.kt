package com.art.studio.weather.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.art.studio.weather.data.model.dailyForecast.DailyForecast
import com.art.studio.weather.databinding.RowDailyForecastLayoutBinding
import com.bumptech.glide.Glide
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class DailyForecastAdapter(private val dailyForecasts: ArrayList<DailyForecast>, private val context: Context): RecyclerView.Adapter<DailyForecastViewHolder>() {
    private val URL = "https://apidev.accuweather.com/developers/Media/Default/WeatherIcons/"
    private var iconDay = ""
    private var iconNight = ""
    private var data: String? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<DailyForecast>) {
        dailyForecasts.clear()
        dailyForecasts.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val binding = RowDailyForecastLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailyForecasts.size
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        val dailyForecasts = dailyForecasts[position]
        data = dailyForecasts.Date
        val inputDateFormat = DateTimeFormatter.ISO_DATE_TIME
        val outputDateFormat = DateTimeFormatter.ofPattern("EEE", Locale.getDefault())
        val inputDate = LocalDateTime.parse(data, inputDateFormat)
        val outputDate = inputDate.format(outputDateFormat)
        holder.binding.tvDailyForecastDate.text = outputDate
        holder.binding.tvDailyForecastDayTemp.text = dailyForecasts.Temperature.Maximum.Value.toString()+"C°"
        holder.binding.tvDailyForecastNightTemp.text = dailyForecasts.Temperature.Minimum.Value.toString()+"C°"
        iconDay = dailyForecasts.Day.Icon.toString()
        iconNight = dailyForecasts.Night.Icon.toString()
        when (iconDay.toInt()) {
            in 1..9 -> iconDay = "0$iconDay-s.png"
            in 10..Int.MAX_VALUE -> iconDay = "$iconDay-s.png"
            else -> println("некорректный URL")
        }
        when (iconNight.toInt()) {
            in 1..9 -> iconNight = "0$iconNight-s.png"
            in 10..Int.MAX_VALUE -> iconNight = "$iconNight-s.png"
            else -> println("некорректный URL")
        }
        Glide
            .with(context)
            .load("$URL$iconDay")
            .centerCrop()
            .into(holder.binding.ivDailyForecastDayIcon)
        Glide
            .with(context)
            .load("$URL$iconNight")
            .centerCrop()
            .into(holder.binding.ivDailyForecastNightIcon)
    }
}


class DailyForecastViewHolder(val binding: RowDailyForecastLayoutBinding): RecyclerView.ViewHolder(binding.root)

