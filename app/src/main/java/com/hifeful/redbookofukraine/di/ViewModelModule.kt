package com.hifeful.redbookofukraine.di

import androidx.lifecycle.ViewModelProvider
import com.hifeful.redbookofukraine.util.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelProviderFactory(viewModelProviderFactory: ViewModelProviderFactory):
            ViewModelProvider.Factory
}