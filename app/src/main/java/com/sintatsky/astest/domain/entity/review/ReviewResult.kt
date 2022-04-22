package com.sintatsky.astest.domain.entity.review


data class ReviewResult(
    val byline: String,
    val critics_pick: Int,
    val date_updated: String,
    val display_title: String,
    val headline: String,
    val link: Link,
    val mpaa_rating: String,
    val multimedia: Multimedia,
    val publication_date: String,
    val summary_short: String,
)