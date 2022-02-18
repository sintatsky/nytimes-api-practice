package com.sintatsky.astest.di.modules


import com.sintatsky.astest.data.repository.ReviewRepositoryImpl
import com.sintatsky.astest.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindReviewRepository(impl: ReviewRepositoryImpl): ReviewRepository

}