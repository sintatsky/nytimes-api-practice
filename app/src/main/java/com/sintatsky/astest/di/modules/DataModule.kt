package com.sintatsky.astest.di.modules


import com.sintatsky.astest.data.repository.ReviewRepositoryImpl
import com.sintatsky.astest.domain.repository.ReviewRepository
import com.sintatsky.astest.domain.usecase.LoadSearchDataUseCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Binds
    fun bindReviewRepository(impl: ReviewRepositoryImpl): ReviewRepository

}