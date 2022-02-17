package com.sintatsky.astest.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sintatsky.astest.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding ?: throw RuntimeException("FragmentSplashBinding is null")

    var splashScreenListener: SplashScreenListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        splashScreenListener = requireContext() as SplashScreenListener
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(2000)
            splashScreenListener?.setupMainFragment()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    interface SplashScreenListener {
        fun setupMainFragment()
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}