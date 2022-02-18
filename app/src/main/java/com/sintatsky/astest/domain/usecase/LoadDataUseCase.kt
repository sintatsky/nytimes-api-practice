package com.sintatsky.astest.domain.usecase

import com.sintatsky.astest.domain.repository.ReviewRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    operator fun invoke() = repository.loadData()
}