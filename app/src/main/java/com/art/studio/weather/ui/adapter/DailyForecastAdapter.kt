package com.art.studio.weather.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.art.studio.weather.data.api.model.dailyForecast.DailyForecast
import com.art.studio.weather.data.api.model.dailyForecast.WeatherForecast
import com.art.studio.weather.databinding.ItemDailyForecastsBinding

class DailyForecastAdapter(private val dailyForecasts: ArrayList<DailyForecast>): RecyclerView.Adapter<DailyForecastViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<DailyForecast>) {
        dailyForecasts.clear()
        dailyForecasts.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        val binding = ItemDailyForecastsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyForecastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dailyForecasts.size
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        val dailyForecasts = dailyForecasts[position]
        holder.binding.dateTv.text = dailyForecasts.Date
        holder.binding.tempTv.text = dailyForecasts.Temperature.Maximum.Value.toString()
//        with(holder)
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
}


class DailyForecastViewHolder(val binding: ItemDailyForecastsBinding): RecyclerView.ViewHolder(binding.root)

