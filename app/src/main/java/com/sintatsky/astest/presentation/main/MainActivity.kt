package com.sintatsky.astest.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sintatsky.astest.R
import com.sintatsky.astest.databinding.ActivityMainBinding
import com.sintatsky.astest.presentation.list.ReviewListFragment
import com.sintatsky.astest.presentation.splash.SplashFragment

class MainActivity : AppCompatActivity(),
    SplashFragment.SplashScreenListener {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupSplashScreen()
    }

    private fun setupSplashScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, SplashFragment.newInstance())
            .commit()
    }

    override fun setupMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, ReviewListFragment.newInstance())
            .commit()
    }
}