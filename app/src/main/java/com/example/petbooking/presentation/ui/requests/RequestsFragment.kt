package com.example.petbooking.presentation.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.petbooking.R
import com.example.petbooking.databinding.RequestsFragmentBinding
import com.example.petbooking.presentation.factories.ViewModelFactory
import com.example.petbooking.presentation.ui.common.BaseFragment
import com.example.petbooking.presentation.viewmodels.RequestsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class RequestsFragment : BaseFragment<RequestsFragmentBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.Factory

    override fun getViewBinding(): RequestsFragmentBinding =
        RequestsFragmentBinding.inflate(layoutInflater)

    private val requestsViewModel: RequestsViewModel by viewModels {
        viewModelFactory.create(0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestsViewModel
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.run {
            tabViewpager.adapter = CustomViewPagerAdapter(
                this@RequestsFragment,
                mutableListOf<Pair<Int, Fragment>>().apply {
                    add(Pair(R.string.active, ActiveRequestsFragment.getInstance()))
                    add(Pair(R.string.cancel, CancelRequestsFragment.getInstance()))
                }
            )
            TabLayoutMediator(requestsTabLayout, tabViewpager) { tab, position ->
                tab.text = createTitleTab(position)
            }.attach()
        }
        selectTab(0)
    }

    private fun createTitleTab(position: Int): String {
        val adapter = (binding.tabViewpager.adapter as? CustomViewPagerAdapter) ?: return ""
        val titleId = adapter.getFragmentTitleId(position)
        return getString(titleId)
    }

    fun selectTab(tabPosition: Int) {
        binding.requestsTabLayout.apply {
            (getTabAt(tabPosition))?.let {
                selectTab(it)
            }
        }
    }
}


class CustomViewPagerAdapter(fragment: Fragment, private val fragmentsList: List<Pair<Int, Fragment>>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int =
        fragmentsList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position].second
    }

    fun getFragmentTitleId(position: Int): Int {
        return fragmentsList[position].first
    }
}