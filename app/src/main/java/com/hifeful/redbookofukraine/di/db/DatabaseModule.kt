package com.hifeful.redbookofukraine.di.db

import android.app.Application
import com.hifeful.redbookofukraine.data.db.RedBookDatabase
import com.hifeful.redbookofukraine.data.db.animal.*
import com.hifeful.redbookofukraine.data.db.organism.OrganismDao
import com.hifeful.redbookofukraine.data.db.organism.OrganismRepository
import com.hifeful.redbookofukraine.data.db.plant.PlantRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AnimalModule::class, PlantModule::class])
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application): RedBookDatabase =
        RedBookDatabase.getInstance(application)

    @Provides
    fun provideOrganismDao(database: RedBookDatabase): OrganismDao = database.organismDao()

    @Provides
    fun provideOrganismRepository(
        organismDao: OrganismDao,
        animalRepository: AnimalRepository,
        plantRepository: PlantRepository
    ): OrganismRepository = OrganismRepository(organismDao, animalRepository, plantRepository)
}