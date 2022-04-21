package com.sintatsky.astest.presentation.bottom_items

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sintatsky.astest.databinding.FragmentSearchBinding
import com.sintatsky.astest.presentation.ReviewApp
import com.sintatsky.astest.presentation.ViewModelFactory
import com.sintatsky.astest.presentation.adapters.SearchListAdapter
import com.sintatsky.astest.presentation.adapters.StateAdapter
import com.sintatsky.astest.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var searchListAdapter: SearchListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ReviewApp).component
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (savedInstanceState == null) {
            _binding = FragmentSearchBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchListAdapter = SearchListAdapter()
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        binding.rvSearchList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchList.adapter = searchListAdapter.withLoadStateHeaderAndFooter(
            header = StateAdapter(),
            footer = StateAdapter()
        )
        val query = binding.search.text.toString()
        Log.d("TAG", "query: $query")
        viewModel.loadSearchData(query)
        lifecycleScope.launch {
            viewModel.list.collect {
                searchListAdapter.submitData(it)
            }
        }


    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}