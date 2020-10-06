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


class ListItemAdapter(val clickListener: ListItemListener) : ListAdapter<DomainCity, ListItemAdapter.ViewHolder>(CityDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, clickListener)
    }

    class ViewHolder private constructor(val binding: ListItemBinding, val clickListener: ListItemListener) : RecyclerView.ViewHolder(binding.root),
        View.OnLongClickListener {
        init {
            binding.listItem.setOnLongClickListener(this)
        }

        fun bind(item: DomainCity) {
            binding.city = item
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

        override fun onLongClick(view: View?): Boolean {
            clickListener.onLongClick(binding.city!!)
            return true
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

class ListItemListener(val clickListener: (city: DomainCity) -> Unit, val longClickListener : (city: DomainCity) -> Unit) {
    fun onClick(city: DomainCity) = clickListener(city)
    fun onLongClick(city: DomainCity) = longClickListener(city)
}