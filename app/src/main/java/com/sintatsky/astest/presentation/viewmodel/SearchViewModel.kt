package com.sintatsky.astest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sintatsky.astest.domain.entity.search.SearchResponse
import com.sintatsky.astest.domain.usecase.LoadSearchUseCase
import com.sintatsky.astest.utils.Event
import com.sintatsky.astest.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val loadSearchUseCase: LoadSearchUseCase
) : ViewModel() {

    private val _searchedArticles = MutableLiveData<Event<Resource<SearchResponse>>>()
    val searchedArticles: LiveData<Event<Resource<SearchResponse>>> = _searchedArticles


    fun searchArticles(q: String) {
        viewModelScope.launch {
        if (q.isEmpty()){
            val response = loadSearchUseCase(" ")
                _searchedArticles.value = Event(response)
        }
            val response = loadSearchUseCase(q)
            _searchedArticles.value = Event(response)
        }
    }
}