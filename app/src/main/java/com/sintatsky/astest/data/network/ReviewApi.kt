package com.sintatsky.astest.data.network

import androidx.annotation.IntRange
import com.sintatsky.astest.BuildConfig.API_KEY
import com.sintatsky.astest.domain.entity.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewApi {

    @GET("reviews/all.json")
    suspend fun getMovieReviews(
        @Query(QUERY_PARAM_OFFSET) page: Int,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY
    ): Response

    companion object {
        private const val QUERY_PARAM_OFFSET = "offset"
        private const val QUERY_PARAM_API_KEY = "api-key"
    }
}