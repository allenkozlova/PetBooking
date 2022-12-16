package com.example.petbooking.presentation.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petbooking.databinding.ActiveRequestsFragmentBinding
import com.example.petbooking.presentation.factories.ViewModelFactory
import com.example.petbooking.presentation.ui.common.BaseFragment
import com.example.petbooking.presentation.utils.Resource
import com.example.petbooking.presentation.viewmodels.ActiveRequestsViewModel
import javax.inject.Inject

class ActiveRequestsFragment : BaseFragment<ActiveRequestsFragmentBinding>() {

    override fun getViewBinding(): ActiveRequestsFragmentBinding = ActiveRequestsFragmentBinding.inflate(layoutInflater)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.Factory

    companion object {
        fun getInstance() = ActiveRequestsFragment()
    }

    private val activeRequestsViewModel: ActiveRequestsViewModel by viewModels {
        viewModelFactory.create(0)
    }

    private val activeRequestsAdapter: RequestsAdapter = RequestsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

        with(binding.activeRequestsList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = activeRequestsAdapter
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            activeRequestsViewModel.stateActiveRequests.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        activeRequestsAdapter.swapList(resource.value)
                    }
                    is Resource.Error -> {
                        val t = 1
                    }
                    is Resource.Loading -> {
                        val t = 2
                    }
                }
            }
        }
    }

}