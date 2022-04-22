package com.sintatsky.astest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sintatsky.astest.data.network.ReviewApi
import com.sintatsky.astest.data.paging.ReviewPagingSource
import com.sintatsky.astest.data.paging.SearchPagingSource
import com.sintatsky.astest.domain.entity.review.ReviewResult
import com.sintatsky.astest.domain.entity.search.Doc
import com.sintatsky.astest.domain.entity.search.SearchResponse
import com.sintatsky.astest.domain.repository.ReviewRepository
import com.sintatsky.astest.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewPagingSource: ReviewPagingSource,
    private val searchPagingSource: SearchPagingSource,
    private val api: ReviewApi
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

    override fun loadSearchData(q: String): Flow<PagingData<Doc>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 10,
                maxSize = 100
            ),
            pagingSourceFactory = {
                searchPagingSource
            }
        ).flow
    }

    override suspend fun loadSearchArticles(q: String): Resource<SearchResponse> {
        return try {
            val response = api.getSearchArticles(q)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("something wrong", null)
            }else{
                Resource.error("something wrong", null)
            }
        }catch (e: Exception) {
            Resource.error("no internet connection", null)
        }
    }


}