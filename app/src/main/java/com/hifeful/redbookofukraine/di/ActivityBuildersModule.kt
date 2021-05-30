package com.hifeful.redbookofukraine.di

import com.hifeful.redbookofukraine.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}