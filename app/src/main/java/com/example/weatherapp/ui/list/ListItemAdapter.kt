package com.example.weatherapp.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.domain.DomainCity


class ListItemAdapter(val clickListener: ListItemListener) : ListAdapter<DomainCity, ListItemAdapter.ViewHolder>(CityDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: DomainCity, clickListener: ListItemListener) {
            binding.city = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
        
    }
}


class CityDiffCallback : DiffUtil.ItemCallback<DomainCity>() {

    override fun areItemsTheSame(oldItem: DomainCity, newItem: DomainCity): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DomainCity, newItem: DomainCity): Boolean {
        return oldItem == newItem
    }
}

class ListItemListener(val clickListener: (city: DomainCity) -> Unit) {
    fun onClick(city: DomainCity) = clickListener(city)
}