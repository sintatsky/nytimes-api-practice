package com.sintatsky.astest.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sintatsky.astest.R
import com.sintatsky.astest.databinding.FragmentContentBinding
import com.sintatsky.astest.presentation.adapters.ViewPagerAdapter
import com.sintatsky.astest.presentation.list.ArticleFragment
import com.sintatsky.astest.presentation.list.ReviewListFragment

class ContentFragment : Fragment() {

    private val fragList = listOf(
        ReviewListFragment.newInstance(),
        ArticleFragment.newInstance(),
        ArticleFragment.newInstance(),
        ArticleFragment.newInstance(),
        ArticleFragment.newInstance()
    )

    private val fragListTitles = listOf(
        "review",
        "article",
        "news",
        "music",
        "video",
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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = ContentFragment()
    }
}