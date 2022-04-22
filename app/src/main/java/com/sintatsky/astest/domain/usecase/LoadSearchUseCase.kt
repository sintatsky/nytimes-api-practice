package com.sintatsky.astest.domain.usecase

import com.sintatsky.astest.domain.repository.ReviewRepository
import javax.inject.Inject

class LoadSearchUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(q: String) = repository.loadSearchArticles(q)
}