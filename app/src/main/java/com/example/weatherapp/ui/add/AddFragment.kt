package com.example.weatherapp.ui.add

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentAddBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


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

        val adapter = AddItemAdapter(AddItemListener { city ->
            viewModel.onCitySelect(city)
        })

        binding.cityList.adapter = adapter

        // set focus to city name text
        binding.cityName.requestFocus()
        // show the keyboard
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.cityName, 0)

        // submit list on change
        viewModel.cities.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // navigate to city
        viewModel.selectedCity.observe(viewLifecycleOwner, Observer {
            it?.let {
                // hide the keyboard
                imm.hideSoftInputFromWindow(binding.cityName.windowToken, 0)
                Toast.makeText(context, getString(R.string.city_added), Toast.LENGTH_LONG).show()
                findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
                viewModel.onCitySelected()
            }
        })

        // change listener for search editText view
        binding.cityName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //viewModel.showCities(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.showCities(s.toString())
            }

        })

        viewModel.allCities.observe(viewLifecycleOwner, {
            viewModel.showCities(binding.cityName.text.toString())
        })


        return binding.root
    }

}