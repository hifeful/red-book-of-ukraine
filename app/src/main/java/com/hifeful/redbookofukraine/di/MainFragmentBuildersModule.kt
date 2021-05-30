package com.hifeful.redbookofukraine.di

import com.hifeful.redbookofukraine.ui.map.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributesMapFragment(): MapFragment
}