package com.sintatsky.astest.di.modules

import android.app.Application
import com.sintatsky.astest.data.database.AppDatabase
import com.sintatsky.astest.data.database.ReviewDao
import com.sintatsky.astest.data.repository.ReviewRepositoryImpl
import com.sintatsky.astest.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindReviewRepository(impl: ReviewRepositoryImpl): ReviewRepository

    companion object{
        @Provides
        fun provideReviewDao(
            application: Application
        ): ReviewDao{
            return AppDatabase.getInstance(application).reviewDao()
        }
    }
}