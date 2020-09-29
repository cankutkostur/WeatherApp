package com.example.weatherapp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.databinding.FragmentListBinding
import timber.log.Timber

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
        viewModel.navigateToAdd.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
            viewModel.addCityCompleted()
            }
        })



        return binding.root
    }
}