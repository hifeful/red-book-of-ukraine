package com.hifeful.redbookofukraine.di

import com.hifeful.redbookofukraine.ui.OrganismFragment
import com.hifeful.redbookofukraine.ui.map.MapFragment
import com.hifeful.redbookofukraine.ui.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributesMapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun contributesOrganismFragment(): OrganismFragment

    @ContributesAndroidInjector
    abstract fun contributesSearchFragment(): SearchFragment
}