package com.hifeful.redbookofukraine.di

import android.app.Application
import com.hifeful.redbookofukraine.data.db.RedBookDatabase
import com.hifeful.redbookofukraine.data.db.animal.*
import com.hifeful.redbookofukraine.data.db.organism.OrganismDao
import com.hifeful.redbookofukraine.data.db.organism.OrganismRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application): RedBookDatabase =
        RedBookDatabase.getInstance(application)

    @Provides
    fun provideAnimalTypeDao(database: RedBookDatabase): AnimalTypeDao = database.animalTypeDao()

    @Provides
    fun provideAnimalClassDao(database: RedBookDatabase): AnimalClassDao = database.animalClassDao()

    @Provides
    fun provideAnimalOrderDao(database: RedBookDatabase): AnimalOrderDao = database.animalOrderDao()

    @Provides
    fun provideAnimalFamilyDao(database: RedBookDatabase): AnimalFamilyDao =
        database.animalFamilyDao()

    @Provides
    fun provideAnimalDao(database: RedBookDatabase): AnimalDao = database.animalDao()

    @Provides
    fun provideOrganismDao(database: RedBookDatabase): OrganismDao = database.organismDao()


    @Provides
    fun provideAnimalTypeRepository(animalTypeDao: AnimalTypeDao): AnimalTypeRepository =
        AnimalTypeRepository(animalTypeDao)

    @Provides
    fun provideAnimalClassRepository(animalClassDao: AnimalClassDao): AnimalClassRepository =
        AnimalClassRepository(animalClassDao)

    @Provides
    fun provideAnimalOrderRepository(animalOrderDao: AnimalOrderDao): AnimalOrderRepository =
        AnimalOrderRepository(animalOrderDao)

    @Provides
    fun provideAnimalFamilyRepository(animalFamilyDao: AnimalFamilyDao): AnimalFamilyRepository =
        AnimalFamilyRepository(animalFamilyDao)

    @Provides
    fun provideAnimalRepository(
        animalDao: AnimalDao,
        animalTypeRepository: AnimalTypeRepository,
        animalClassRepository: AnimalClassRepository,
        animalOrderRepository: AnimalOrderRepository,
        animalFamilyRepository: AnimalFamilyRepository
    ): AnimalRepository = AnimalRepository(
        animalDao,
        animalTypeRepository,
        animalClassRepository,
        animalOrderRepository,
        animalFamilyRepository
    )

    @Provides
    fun provideOrganismRepository(
        organismDao: OrganismDao,
        animalRepository: AnimalRepository
    ): OrganismRepository = OrganismRepository(organismDao, animalRepository)
}