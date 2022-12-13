package com.example.petbooking.presentation.ui.main_screen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petbooking.databinding.MainScreenFragmentBinding
import com.example.petbooking.presentation.factories.ViewModelFactory
import com.example.petbooking.presentation.ui.main_screen.adapters.SittersMainScreenAdapter
import com.example.petbooking.presentation.ui.common.BaseFragment
import com.example.petbooking.presentation.utils.Resource
import com.example.petbooking.presentation.viewmodels.MainScreenViewModel
import javax.inject.Inject

class MainScreenFragment : BaseFragment<MainScreenFragmentBinding>(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.Factory

    override fun getViewBinding(): MainScreenFragmentBinding = MainScreenFragmentBinding.inflate(layoutInflater)

    private val mainScreenViewModel: MainScreenViewModel by viewModels {
        viewModelFactory.create(0)
    }

    private val dataAdapter = SittersMainScreenAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

        dataAdapter.addMap()

        with(binding.sittersList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dataAdapter
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainScreenViewModel.stateSitters.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        dataAdapter.swapList(resource.value)
                        binding.sittersList.isVisible = true
                        binding.shimmerLayout.stopShimmer()
                        binding.shimmerLayout.isVisible = false
                    }
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {
                        binding.sittersList.isVisible = false
                        binding.shimmerLayout.isVisible = true
                        binding.shimmerLayout.startShimmer()
                    }
                }
            }
        }
    }
}