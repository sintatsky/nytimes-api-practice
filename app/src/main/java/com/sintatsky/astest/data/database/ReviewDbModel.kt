package com.sintatsky.astest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review_list")
data class ReviewDbModel(
    val byline: String,
    val critics_pick: Int,
    val date_updated: String,
    @PrimaryKey
    val display_title: String,
    val headline: String,
    val link: String,
    val mpaa_rating: String,
    val multimedia: String,
    val publication_date: String,
    val summary_short: String,
)