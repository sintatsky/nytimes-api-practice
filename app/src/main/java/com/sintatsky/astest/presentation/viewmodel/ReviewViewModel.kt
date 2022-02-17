package com.sintatsky.astest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sintatsky.astest.domain.usecase.GetReviewListUseCase
import com.sintatsky.astest.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewViewModel @Inject constructor(
    private val getReviewListUseCase: GetReviewListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {


    val reviewList = getReviewListUseCase()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}