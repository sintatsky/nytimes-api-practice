package com.sintatsky.astest.data.mapper

import com.sintatsky.astest.data.database.ReviewDbModel
import com.sintatsky.astest.data.network.ReviewDto
import com.sintatsky.astest.domain.entity.Link
import com.sintatsky.astest.domain.entity.Multimedia
import com.sintatsky.astest.domain.entity.ReviewResult
import javax.inject.Inject

class ReviewMapper @Inject constructor() {

    fun mapFromEntityToDbModel(type: ReviewResult) = ReviewDbModel(
        byline = type.byline,
        critics_pick = type.critics_pick,
        date_updated = type.date_updated,
        display_title = type.display_title,
        headline = type.headline,
        link = type.link.url,
        mpaa_rating = type.mpaa_rating,
        multimedia = type.multimedia.src,
        publication_date = type.publication_date,
        summary_short = type.summary_short
    )

    fun mapFromEntityToDto(type: ReviewResult) = ReviewDto(
        byline = type.byline,
        critics_pick = type.critics_pick,
        date_updated = type.date_updated,
        display_title = type.display_title,
        headline = type.headline,
        link = type.link.url,
        mpaa_rating = type.mpaa_rating,
        multimedia = type.multimedia.src,
        publication_date = type.publication_date,
        summary_short = type.summary_short
    )

    fun mapFromDtoToDbModel(type: ReviewDto) = ReviewDbModel(
        byline = type.byline,
        critics_pick = type.critics_pick,
        date_updated = type.date_updated,
        display_title = type.display_title,
        headline = type.headline,
        link = type.link,
        mpaa_rating = type.mpaa_rating,
        multimedia = type.multimedia,
        publication_date = type.publication_date,
        summary_short = type.summary_short
    )


    fun mapFromDbModelToEntity(dbModel: ReviewDbModel) = ReviewResult(
        byline = dbModel.byline,
        critics_pick = dbModel.critics_pick,
        date_updated = dbModel.date_updated,
        display_title = dbModel.display_title,
        headline = dbModel.headline,
        link = Link(dbModel.link),
        mpaa_rating = dbModel.mpaa_rating,
        multimedia = Multimedia(dbModel.multimedia),
        publication_date = dbModel.publication_date,
        summary_short = dbModel.summary_short
    )
}