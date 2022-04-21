package com.sintatsky.astest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sintatsky.astest.domain.usecase.LoadSearchDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val loadSearchDataUseCase: LoadSearchDataUseCase
) : ViewModel() {
    private var q = ""

    val list = loadSearchDataUseCase(q)

    fun loadSearchData(q: String) {
        viewModelScope.launch {
            loadSearchDataUseCase(q)
        }
    }
}