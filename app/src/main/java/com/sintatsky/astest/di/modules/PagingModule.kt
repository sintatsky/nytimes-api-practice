package com.sintatsky.astest.di.modules

import androidx.paging.PagingSource
import com.sintatsky.astest.data.paging.ReviewPagingSource
import com.sintatsky.astest.domain.entity.ReviewResult
import dagger.Binds
import dagger.Module

@Module
interface PagingModule {

    @Binds
    fun bindReviewPagingSource(
        reviewPagingSource: ReviewPagingSource
    ): PagingSource<Int, ReviewResult>
}