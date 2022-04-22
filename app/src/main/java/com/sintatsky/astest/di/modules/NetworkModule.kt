package com.sintatsky.astest.di.modules

import com.sintatsky.astest.data.network.ReviewApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideReviewApi(): ReviewApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ReviewApi::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/svc/"
    }
}