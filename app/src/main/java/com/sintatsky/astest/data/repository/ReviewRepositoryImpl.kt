package com.sintatsky.astest.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sintatsky.astest.data.database.ReviewDao
import com.sintatsky.astest.data.mapper.ReviewMapper
import com.sintatsky.astest.data.network.ReviewApi
import com.sintatsky.astest.domain.entity.ReviewResult
import com.sintatsky.astest.domain.repository.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val mapper: ReviewMapper,
    private val reviewDao: ReviewDao,
    private val reviewApi: ReviewApi
) : ReviewRepository {

    override fun getReviewList(): LiveData<List<ReviewResult>> {
        return Transformations.map(reviewDao.getReviewList()) {
            it.map { mapper.mapFromDbModelToEntity(it) }
        }
    }

    override suspend fun loadData() {
            val reviewList = reviewApi.getMovieReviews(1)
            reviewDao.insertReviewList(
                reviewList.results.map {
                    Log.d("LOAD_DATA", it.toString())
                    mapper.mapFromEntityToDbModel(it)
                }
            )
    }
}