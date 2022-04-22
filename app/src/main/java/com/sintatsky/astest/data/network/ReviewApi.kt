package com.sintatsky.astest.data.network

import androidx.lifecycle.LiveData
import com.sintatsky.astest.BuildConfig.API_KEY
import com.sintatsky.astest.domain.entity.review.Response
import com.sintatsky.astest.domain.entity.search.SearchResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

interface ReviewApi {

    @GET("movies/v2/reviews/all.json")
    suspend fun getMovieReviews(
        @Query(QUERY_PARAM_OFFSET) page: Int,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): Response

    @GET("search/v2/articlesearch.json")
    suspend fun getSearchArticles(
        @Query(QUERY_PARAM_OFFSET) page: Int,
        @Query(QUERY_PARAM_SEARCH) q: String = "",
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): SearchResponse

    @GET("search/v2/articlesearch.json")
    suspend fun getSearchArticles(
        @Query(QUERY_PARAM_SEARCH) q: String,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): retrofit2.Response<SearchResponse>


    companion object {
        private const val QUERY_PARAM_OFFSET = "offset"
        private const val QUERY_PARAM_SEARCH = "q"
        private const val QUERY_PARAM_API_KEY = "api-key"
    }
}