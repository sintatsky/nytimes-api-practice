package com.sintatsky.astest.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sintatsky.astest.R
import com.sintatsky.astest.databinding.ActivityMainBinding
import com.sintatsky.astest.presentation.bottom_items.BookmarksFragment
import com.sintatsky.astest.presentation.bottom_items.ContentFragment
import com.sintatsky.astest.presentation.bottom_items.ProfileFragment
import com.sintatsky.astest.presentation.bottom_items.SupportFragment


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_nav_content -> {
                    setupFragment(ContentFragment.newInstance())
                    true
                }
                R.id.bottom_nav_profile -> {
                    setupFragment(ProfileFragment.newInstance())
                    true
                }
                R.id.bottom_nav_support -> {
                    setupFragment(SupportFragment.newInstance())
                    true
                }
                R.id.bottom_nav_bookmarks -> {
                    setupFragment(BookmarksFragment.newInstance())
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.bottom_nav_content
        }
    }

    private fun setupFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .commit()
    }
}