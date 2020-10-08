package com.example.weatherapp.ui.detail

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DayItemBinding
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.databinding.HourItemBinding
import com.example.weatherapp.domain.DomainHourly

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val selectedCity = DetailFragmentArgs.fromBundle(requireArguments()).selectedCity
        val viewModelFactory = DetailViewModelFactory(selectedCity, Application())

        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = HourItemAdapter()

        binding.cityWeatherHourly.adapter = adapter

        viewModel.selectedCity.observe(viewLifecycleOwner, {
            adapter.submitList(it.hourly)
        })

        viewModel.selectedCity.observe(viewLifecycleOwner, {
            it.daily.forEach { day ->
                val parent = binding.forecastsList
                val layoutInflater = LayoutInflater.from(parent.context)
                val dayBinding = DayItemBinding.inflate(layoutInflater, parent, true)
                dayBinding.item = day
            }
        })


        return binding.root
    }
}