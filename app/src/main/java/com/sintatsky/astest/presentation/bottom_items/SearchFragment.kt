package com.sintatsky.astest.presentation.bottom_items

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sintatsky.astest.R
import com.sintatsky.astest.databinding.FragmentSearchBinding
import com.sintatsky.astest.presentation.ReviewApp
import com.sintatsky.astest.presentation.ViewModelFactory
import com.sintatsky.astest.presentation.adapters.SearchArticlesAdapter
import com.sintatsky.astest.presentation.tab_items.ReviewDetailFragment
import com.sintatsky.astest.presentation.viewmodel.SearchViewModel
import com.sintatsky.astest.utils.Status
import javax.inject.Inject


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var searchArticlesAdapter: SearchArticlesAdapter

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
        searchArticlesAdapter = SearchArticlesAdapter()
        searchArticlesAdapter.onSearchArticleItemSelectedListener =
            object : SearchArticlesAdapter.OnSearchArticleItemSelectedListener{
                override fun onArticleItemClick(link: String) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer, ReviewDetailFragment.newInstance(link))
                        .commit()
                }

            }
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        binding.rvSearchList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchList.adapter = searchArticlesAdapter
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    viewModel.searchArticles(s.toString())
                }
            }
        })

        viewModel.searchedArticles.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        val articles = result.data?.response?.docs
                        searchArticlesAdapter.articles = articles ?: listOf()
                        if (searchArticlesAdapter.articles.isEmpty()) {
                            binding.rvSearchList.visibility = View.GONE
                        } else binding.rvSearchList.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        binding.rvSearchList.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.rvSearchList.visibility = View.GONE
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}