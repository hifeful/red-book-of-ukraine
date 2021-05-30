package com.hifeful.redbookofukraine.di

import androidx.lifecycle.ViewModel
import com.hifeful.redbookofukraine.ui.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(profileViewModel: MapViewModel): ViewModel

}