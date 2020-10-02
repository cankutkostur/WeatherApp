package com.example.weatherapp.ui.add

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.AddItemBinding
import com.example.weatherapp.domain.DomainCity

class AddItemAdapter(val clickListener: AddItemListener) : ListAdapter<DomainCity, AddItemAdapter.ViewHolder>(CityDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: AddItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: DomainCity, clickListener: AddItemListener) {
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


class CityDiffCallback : DiffUtil.ItemCallback<DomainCity>() {

    override fun areItemsTheSame(oldItem: DomainCity, newItem: DomainCity): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DomainCity, newItem: DomainCity): Boolean {
        return oldItem == newItem
    }
}

class AddItemListener(val clickListener: (city: DomainCity) -> Unit) {
    fun onClick(city: DomainCity) = clickListener(city)
}