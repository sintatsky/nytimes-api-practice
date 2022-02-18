package com.sintatsky.astest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sintatsky.astest.data.paging.ReviewPagingSource
import com.sintatsky.astest.domain.entity.ReviewResult
import com.sintatsky.astest.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewPagingSource: ReviewPagingSource,
) : ReviewRepository {

    override fun loadData(): Flow<PagingData<ReviewResult>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 10,
                maxSize = 100
            ),
            pagingSourceFactory = {
                reviewPagingSource
            }
        ).flow
    }
}