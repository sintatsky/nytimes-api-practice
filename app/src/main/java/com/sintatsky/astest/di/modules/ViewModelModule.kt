package com.sintatsky.astest.di.modules

import androidx.lifecycle.ViewModel
import com.sintatsky.astest.di.annotation.ViewModelKey
import com.sintatsky.astest.presentation.viewmodel.ReviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReviewViewModel::class)
    fun bindReviewViewModel(viewModel: ReviewViewModel): ViewModel
}