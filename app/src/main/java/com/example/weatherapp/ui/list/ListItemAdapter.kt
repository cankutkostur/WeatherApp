package com.example.weatherapp.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.domain.DomainCity
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily


class ListItemAdapter(val clickListener: ListItemListener) : ListAdapter<DomainCityWithHourlyAndDaily, ListItemAdapter.ViewHolder>(CityDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener.onClick(item)
        }
        holder.itemView.setOnLongClickListener{
            clickListener.onLongClick(item)
        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemBinding, val clickListener: ListItemListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DomainCityWithHourlyAndDaily) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, clickListener: ListItemListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, clickListener)
            }
        }

    }
}


class CityDiffCallback : DiffUtil.ItemCallback<DomainCityWithHourlyAndDaily>() {

    override fun areItemsTheSame(oldItem: DomainCityWithHourlyAndDaily, newItem: DomainCityWithHourlyAndDaily): Boolean {
        return oldItem.city.id == newItem.city.id
    }

    override fun areContentsTheSame(oldItem: DomainCityWithHourlyAndDaily, newItem: DomainCityWithHourlyAndDaily): Boolean {
        return oldItem == newItem
    }
}

class ListItemListener(val clickListener: (city: DomainCityWithHourlyAndDaily) -> Unit,
                       val longClickListener : (city: DomainCityWithHourlyAndDaily) -> Boolean) {
    fun onClick(city: DomainCityWithHourlyAndDaily) = clickListener(city)
    fun onLongClick(city: DomainCityWithHourlyAndDaily) = longClickListener(city)
}