package com.sintatsky.astest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sintatsky.astest.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val reviewList = loadDataUseCase()

    init {
        loadData()
    }

     private fun loadData() {
        viewModelScope.launch {
           loadDataUseCase()
            Log.d("LOG", "ReviewViewModel: ${reviewList}")
        }
    }
}