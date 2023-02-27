package com.art.studio.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.art.studio.weather.data.api.model.dailyForecast.DailyForecast
import com.art.studio.weather.databinding.ItemDailyForecastsBinding
import com.art.studio.weather.ui.MainViewModel

class DailyForecastAdapter(var dailyForecasts: List<DailyForecast>): RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val binding = ItemDailyForecastsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailyForecasts.size
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        val dailyForecast = dailyForecasts[position]
        holder.binding.dateTv.text = dailyForecast.date
        holder.binding.tempTv.text = dailyForecast.temperature.maximum.value.toString()
//        with(holder){
//            with(dailyForecasts){
//                binding.dateTv.text = dailyForecasts.get(position).date
//                binding.tempTv.text = dailyForecasts.get(position).temperature.toString()
//                // GlideApp.with(holder.itemView.context)
//                    //.load(badgeUrl)
//                    //.into(binding.topLearnerImage)
//                holder.itemView.setOnClickListener {
//                    Toast.makeText(holder.itemView.context, dailyForecasts.get(position).mobileLink,Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
    }

    inner class DailyForecastViewHolder(val binding: ItemDailyForecastsBinding): RecyclerView.ViewHolder(binding.root)
}
