package com.sintatsky.astest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewDao {

    @Query("SELECT * FROM review_list")
    fun getReviewList(): LiveData<List<ReviewDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviewList(reviewList: List<ReviewDbModel>)
}