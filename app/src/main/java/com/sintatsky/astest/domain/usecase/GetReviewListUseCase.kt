package com.sintatsky.astest.domain.usecase

import com.sintatsky.astest.domain.repository.ReviewRepository
import javax.inject.Inject

class GetReviewListUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke() = repository.getReviewList()
}