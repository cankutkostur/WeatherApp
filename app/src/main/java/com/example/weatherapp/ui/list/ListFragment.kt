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
import com.example.weatherapp.R
import com.example.weatherapp.database.models.asDomainModel
import com.example.weatherapp.databinding.FragmentListBinding
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
                adapter.submitList(it)
            }
        })

        viewModel.isSelecting.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, "selecting", Toast.LENGTH_SHORT).show()
                activity!!.findViewById<ImageView>(R.id.delete_icon).visibility = View.VISIBLE
            }
            else{
                activity!!.findViewById<ImageView>(R.id.delete_icon).visibility = View.INVISIBLE
            }
        })

        viewModel.navigateToAdd.observe(viewLifecycleOwner, {
            if (it){
                findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
                viewModel.addCityCompleted()
            }
        })




        return binding.root
    }
}