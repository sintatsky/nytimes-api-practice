package com.sintatsky.astest.domain.usecase

import com.sintatsky.astest.domain.repository.ReviewRepository
import javax.inject.Inject

class LoadSearchDataUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke(q: String) = repository.loadSearchData(q)
}