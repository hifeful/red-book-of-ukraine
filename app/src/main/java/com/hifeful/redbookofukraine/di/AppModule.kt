package com.hifeful.redbookofukraine.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hifeful.redbookofukraine.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    internal fun provideRequestOptions() =
        RequestOptions.placeholderOf(R.drawable.white_background)
            .error(R.drawable.white_background)

    @Singleton
    @Provides
    internal fun provideGlideInstance(application: Application, requestOptions: RequestOptions) =
        Glide.with(application).setDefaultRequestOptions(requestOptions)
}