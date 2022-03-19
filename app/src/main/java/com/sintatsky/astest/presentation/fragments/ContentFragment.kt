package com.sintatsky.astest.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.sintatsky.astest.R
import com.sintatsky.astest.databinding.FragmentContentBinding
import com.sintatsky.astest.presentation.list.ArticleFragment
import com.sintatsky.astest.presentation.list.ReviewListFragment

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding: FragmentContentBinding
        get() = _binding ?: throw RuntimeException("FragmentContentBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState == null){
            _binding = FragmentContentBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.tabLayout) {
            addTab(newTab().setText("review"))
            addTab(newTab().setText("ARTICLE"))
            addTab(newTab().setText("ART"))
            addTab(newTab().setText("MUSIC"))
            addTab(newTab().setText("VIDEO"))
            addTab(newTab().setText("MUSEUM"))
            addTab(newTab().setText("THEATER"))
            addTab(newTab().setText("NEWS"))
            if (savedInstanceState == null){
                selectTab(getTabAt(0))
                replaceFragment(ReviewListFragment.newInstance())
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (selectedTabPosition) {
                        0 -> replaceFragment(ReviewListFragment.newInstance())
                        1 -> replaceFragment(ArticleFragment.newInstance())
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.contentContainer, fragment)
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