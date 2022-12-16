package com.example.petbooking.presentation.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petbooking.databinding.CancelRequestsFragmentBinding
import com.example.petbooking.presentation.factories.ViewModelFactory
import com.example.petbooking.presentation.ui.common.BaseFragment
import com.example.petbooking.presentation.utils.Resource
import com.example.petbooking.presentation.viewmodels.CancelRequestsViewModel
import javax.inject.Inject

class CancelRequestsFragment : BaseFragment<CancelRequestsFragmentBinding>() {

    override fun getViewBinding(): CancelRequestsFragmentBinding = CancelRequestsFragmentBinding.inflate(layoutInflater)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.Factory

    companion object {
        fun getInstance() = CancelRequestsFragment()
    }

    private val activeRequestsViewModel: CancelRequestsViewModel by viewModels {
        viewModelFactory.create(0)
    }

    private val activeRequestsAdapter: RequestsAdapter = RequestsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

        with(binding.cancelRequestsList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = activeRequestsAdapter
        }
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            activeRequestsViewModel.stateCancelRequests.collect { resource ->
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