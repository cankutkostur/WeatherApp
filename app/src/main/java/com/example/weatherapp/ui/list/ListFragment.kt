package com.example.weatherapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.weatherapp.R
import com.example.weatherapp.database.models.asDomainModel
import com.example.weatherapp.databinding.FragmentListBinding
import com.example.weatherapp.domain.setSelected
import kotlinx.android.synthetic.main.activity_main.view.*

class ListFragment : Fragment() {

    // Lazily initialize viewModel
    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListBinding.inflate(inflater)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // bind viewModel
        binding.viewModel = viewModel

        val adapter = ListItemAdapter(ListItemListener( { city ->
            viewModel.onCityClicked(city)
        }, { city ->
            viewModel.onSelect(city)
        }))

        binding.cityList.adapter = adapter

        viewModel.favCities.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it.setSelected(viewModel.selectedCities.value!!))
                adapter.notifyDataSetChanged()
            }
        })

        viewModel.selectedCities.observe(viewLifecycleOwner, {
            adapter.submitList(viewModel.favCities.value?.setSelected(it))
            adapter.notifyDataSetChanged()
        })

        viewModel.isSelecting.observe(viewLifecycleOwner, {
            if (it) {
                requireActivity().findViewById<ImageView>(R.id.delete_icon).visibility = View.VISIBLE
            }
            else{
                requireActivity().findViewById<ImageView>(R.id.delete_icon).visibility = View.GONE
            }
        })

        viewModel.navigateToAdd.observe(viewLifecycleOwner, {
            if (it){
                findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
                viewModel.addCityCompleted()
            }
        })

        viewModel.navigateToCity.observe(viewLifecycleOwner, {
            it?.let {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it))
                viewModel.onCityNavigated()
            }
        })

        requireActivity().findViewById<ImageView>(R.id.delete_icon).setOnClickListener{
            Toast.makeText(context, getString(R.string.city_deleted), Toast.LENGTH_LONG).show()
            viewModel.onDelete()
        }

        return binding.root
    }
}