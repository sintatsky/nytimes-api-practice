package com.sintatsky.astest.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sintatsky.astest.presentation.list.ArticleFragment
import com.sintatsky.astest.presentation.list.ReviewListFragment

class ViewPagerAdapter(
    fragmentActivity:FragmentActivity,
    private val list: List<Fragment>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}