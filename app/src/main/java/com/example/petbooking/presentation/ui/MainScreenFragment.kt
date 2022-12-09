package com.example.petbooking.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petbooking.databinding.MainScreenFragmentBinding
import com.example.petbooking.presentation.factories.ViewModelFactory
import com.example.petbooking.presentation.utils.Status
import com.example.petbooking.presentation.viewmodels.MainScreenViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainScreenFragment : Fragment(){

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.Factory

    private var _binding: MainScreenFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainScreenViewModel: MainScreenViewModel by viewModels {
        viewModelFactory.create(0)
    }

    private val dataAdapter = SittersMainScreenAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()

        with(binding.sittersList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dataAdapter
        }
    }

    private fun setupObserver() {
        mainScreenViewModel.getUsers().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { list ->
                        dataAdapter.swapList(list)
                    }
                }
                Status.LOADING -> {
                    val t = 1
                }
                Status.ERROR -> {
                    //Handle Error
                    val t = 1
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}