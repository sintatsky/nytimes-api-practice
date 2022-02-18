package com.sintatsky.astest.domain.repository

import androidx.paging.PagingData
import com.sintatsky.astest.domain.entity.ReviewResult
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    fun loadData(): Flow<PagingData<ReviewResult>>
}