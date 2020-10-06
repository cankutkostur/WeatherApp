package com.example.weatherapp.ui.add

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.util.JsonCity
import com.example.weatherapp.databinding.AddItemBinding

class AddItemAdapter(val clickListener: AddItemListener) : ListAdapter<JsonCity, AddItemAdapter.ViewHolder>(CityDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: AddItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: JsonCity, clickListener: AddItemListener) {
            binding.city = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AddItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class CityDiffCallback : DiffUtil.ItemCallback<JsonCity>() {

    override fun areItemsTheSame(oldItem: JsonCity, newItem: JsonCity): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: JsonCity, newItem: JsonCity): Boolean {
        return oldItem == newItem
    }
}

class AddItemListener(val clickListener: (city: JsonCity) -> Unit) {
    fun onClick(city: JsonCity) = clickListener(city)
}