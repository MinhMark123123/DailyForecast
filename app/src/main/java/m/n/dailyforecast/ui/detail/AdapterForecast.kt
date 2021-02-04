package m.n.dailyforecast.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import m.n.dailyforecast.R
import m.n.dailyforecast.data.Daily
import m.n.dailyforecast.databinding.ItemForecastBinding
import m.n.dailyforecast.utils.AppGlide
import m.n.dailyforecast.utils.GlideApp.with
import m.n.dailyforecast.utils.formatSimple
import m.n.dailyforecast.utils.fromFtoC
import m.n.dailyforecast.utils.toDateTime
import java.util.*


class AdapterForecast : ListAdapter<Daily, AdapterForecast.ViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemForecastBinding = ItemForecastBinding.bind(itemView)

        fun bindData(data: Daily) {
            val weather = data.weather.first()
            val context = binding.root.context.applicationContext
            binding.date.text = data.dt.toDateTime().formatSimple(context, R.string.date_format)
            binding.temperature.text = data.temp.day.fromFtoC().toString().formatSimple(context, R.string.temperature_format)
            binding.pressure.text = data.pressure.toString().formatSimple(context, R.string.pressure_format)
            binding.humidity.text = data.humidity.toString().formatSimple(context, R.string.humidity_format)
            binding.description.text = weather.description.toUpperCase(Locale.ROOT)
            Glide
                .with(binding.icon.context)
                .load("https://openweathermap.org/img/wn/${weather.icon}@2x.png")
                .centerCrop()
                .into(binding.icon)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Daily>() {
            override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem.dt == newItem.dt
            }

            override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem == newItem
            }

        }
    }

}