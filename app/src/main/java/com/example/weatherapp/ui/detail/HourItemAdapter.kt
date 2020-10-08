package com.example.weatherapp.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.HourItemBinding
import com.example.weatherapp.domain.DomainHourly


class HourItemAdapter() : ListAdapter<DomainHourly, HourItemAdapter.ViewHolder>(CityDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: HourItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: DomainHourly) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HourItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class CityDiffCallback : DiffUtil.ItemCallback<DomainHourly>() {

    override fun areItemsTheSame(oldItem: DomainHourly, newItem: DomainHourly): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: DomainHourly, newItem: DomainHourly): Boolean {
        return oldItem == newItem
    }
}