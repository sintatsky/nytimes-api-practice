package com.sintatsky.astest.domain.repository

import androidx.lifecycle.LiveData
import com.sintatsky.astest.domain.entity.ReviewResult

interface ReviewRepository {

    fun getReviewList(): LiveData<List<ReviewResult>>

    suspend fun loadData()
}