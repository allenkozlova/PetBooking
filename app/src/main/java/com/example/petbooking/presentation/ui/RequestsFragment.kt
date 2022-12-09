package com.example.petbooking.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petbooking.databinding.RequestsFragmentBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RequestsFragment : Fragment() {

    private var _binding: RequestsFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RequestsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}