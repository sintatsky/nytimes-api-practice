package com.sintatsky.astest.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sintatsky.astest.presentation.list.ArticleFragment
import com.sintatsky.astest.presentation.list.ReviewListFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ReviewListFragment.newInstance()
            1 -> ArticleFragment.newInstance()
            else -> ArticleFragment.newInstance()
        }
    }
}