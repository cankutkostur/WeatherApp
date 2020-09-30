package com.example.weatherapp.ui.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    // Lazily initialize viewModel
    private val viewModel: AddViewModel by lazy {
        ViewModelProvider(this).get(AddViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel


        // set focus to city name text
        binding.cityName.requestFocus()

        // show the keyboard
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.cityName, 0)

        return binding.root
    }


}