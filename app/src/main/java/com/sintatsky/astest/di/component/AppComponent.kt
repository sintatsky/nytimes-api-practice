package com.sintatsky.astest.di.component

import android.app.Application
import com.sintatsky.astest.di.modules.DataModule
import com.sintatsky.astest.di.modules.NetworkModule
import com.sintatsky.astest.di.modules.ViewModelModule
import com.sintatsky.astest.presentation.tab_items.ReviewListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        NetworkModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    fun inject(fragment: ReviewListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}