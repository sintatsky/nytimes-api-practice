package com.sintatsky.astest.presentation.bottom_items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sintatsky.astest.R
import com.sintatsky.astest.databinding.FragmentContentBinding
import com.sintatsky.astest.presentation.adapters.ViewPagerAdapter
import com.sintatsky.astest.presentation.tab_items.*

class ContentFragment : Fragment() {

    private val fragList = listOf(
        ReviewListFragment.newInstance(),
        ArtsFragment.newInstance(),
        HomeFragment.newInstance(),
        ScienceFragment.newInstance(),
        UsNewsFragment.newInstance(),
        WorldNewsFragment.newInstance()
    )

    private val fragListTitles = listOf(
        "reviews",
        "arts",
        "home",
        "science",
        "us",
        "world"
    )

    private var _binding: FragmentContentBinding? = null
    private val binding: FragmentContentBinding
        get() = _binding ?: throw RuntimeException("FragmentContentBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState == null) {
            _binding = FragmentContentBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchTopLine()
        val adapter = ViewPagerAdapter(requireActivity(), fragList)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, pos -> tab.text = fragListTitles[pos]
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (ReviewListFragment.newInstance().isAdded){
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.viewPager, fragList[tab?.position!!])
                        .commit()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun launchTopLine() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.searchFrame, SearchFragment.newInstance())
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = ContentFragment()
    }
}