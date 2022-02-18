package com.sintatsky.astest.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sintatsky.astest.databinding.FragmentReviewListBinding
import com.sintatsky.astest.presentation.adapters.ReviewListAdapter
import com.sintatsky.astest.presentation.adapters.StateAdapter
import com.sintatsky.astest.presentation.ReviewApp
import com.sintatsky.astest.presentation.ViewModelFactory
import com.sintatsky.astest.presentation.viewmodel.ReviewViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class ReviewListFragment : Fragment() {

    private lateinit var viewModel: ReviewViewModel
    private lateinit var reviewAdapter: ReviewListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ReviewApp).component
    }

    private var _binding: FragmentReviewListBinding? = null
    private val binding: FragmentReviewListBinding
        get() = _binding ?: throw RuntimeException("FragmentReviewListBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reviewAdapter = ReviewListAdapter()
        binding.rvReviewList.adapter = reviewAdapter.withLoadStateHeaderAndFooter(
            header = StateAdapter(),
            footer = StateAdapter { reviewAdapter.retry() })
        viewModel = ViewModelProvider(this, viewModelFactory)[ReviewViewModel::class.java]

        lifecycleScope.launch {
            viewModel.reviewList.collect {
                reviewAdapter.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = ReviewListFragment()
    }
}