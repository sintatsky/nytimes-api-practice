package com.sintatsky.astest.di.component

import android.app.Application
import com.sintatsky.astest.di.modules.DataModule
import com.sintatsky.astest.di.modules.NetworkModule
import com.sintatsky.astest.di.modules.PagingModule
import com.sintatsky.astest.di.modules.ViewModelModule
import com.sintatsky.astest.presentation.list.ReviewListFragment
import com.sintatsky.astest.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        PagingModule::class]
)
interface AppComponent {

    fun inject(fragment: ReviewListFragment)
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}