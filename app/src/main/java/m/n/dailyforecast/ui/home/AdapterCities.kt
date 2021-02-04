package m.n.dailyforecast.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import m.n.dailyforecast.R
import m.n.dailyforecast.data.CityLocation
import m.n.dailyforecast.databinding.ItemCityBinding
import m.n.dailyforecast.utils.formatSimple

typealias  SearchItemClicked = (View, CityLocation) -> Unit

class AdapterCities :
    ListAdapter<CityLocation, AdapterCities.ViewHolder>(AdapterCities.diffUtil) {
    var clickHandler: SearchItemClicked? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        )
        viewHolder.binding.root.setOnClickListener {
            clickHandler?.invoke(it, getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindData(getItem(position))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemCityBinding = ItemCityBinding.bind(itemView)

        fun bindData(data: CityLocation) {
            Log.d("mmm", data.toString())
            binding.countryName.text = data.components.country.formatSimple(binding.root.context, R.string.country_format)
            binding.name.text = (data.components.city?:data.formatted).formatSimple(binding.root.context, R.string.city_format)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CityLocation>() {
            override fun areItemsTheSame(oldItem: CityLocation, newItem: CityLocation): Boolean {
                return oldItem.components.postcode == newItem.components.postcode
            }

            override fun areContentsTheSame(oldItem: CityLocation, newItem: CityLocation): Boolean {
                return oldItem == newItem
            }

        }
    }


}