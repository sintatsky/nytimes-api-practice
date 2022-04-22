package com.sintatsky.astest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sintatsky.astest.di.annotation.ViewModelKey
import com.sintatsky.astest.presentation.ViewModelFactory
import com.sintatsky.astest.presentation.viewmodel.ReviewViewModel
import com.sintatsky.astest.presentation.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReviewViewModel::class)
    fun bindReviewViewModel(viewModel: ReviewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

}