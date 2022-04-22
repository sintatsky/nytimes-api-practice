package com.sintatsky.astest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sintatsky.astest.BuildConfig.API_KEY
import com.sintatsky.astest.data.network.ReviewApi
import com.sintatsky.astest.domain.entity.review.ReviewResult
import javax.inject.Inject

class ReviewPagingSource @Inject constructor(
    private val reviewApi: ReviewApi
) : PagingSource<Int, ReviewResult>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewResult>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1)
            ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewResult> {
        val pageIndex = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = reviewApi.getMovieReviews(pageIndex, API_KEY)
            val reviews = response.results
            val nextKey = if (reviews.isEmpty()) null else pageIndex + DEFAULT_OFFSET_VALUE
            val prevKey = if (pageIndex > DEFAULT_PAGE_INDEX) pageIndex - DEFAULT_OFFSET_VALUE else null
            LoadResult.Page(reviews, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 0
        const val DEFAULT_OFFSET_VALUE = 20
    }
}