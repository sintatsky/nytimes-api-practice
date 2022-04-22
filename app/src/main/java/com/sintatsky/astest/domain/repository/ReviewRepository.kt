package com.sintatsky.astest.domain.repository

import androidx.paging.PagingData
import com.sintatsky.astest.domain.entity.review.ReviewResult
import com.sintatsky.astest.domain.entity.search.Doc
import com.sintatsky.astest.domain.entity.search.SearchResponse
import com.sintatsky.astest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    fun loadData(): Flow<PagingData<ReviewResult>>

    fun loadSearchData(q: String): Flow<PagingData<Doc>>

    suspend fun loadSearchArticles(q: String): Resource<SearchResponse>
}