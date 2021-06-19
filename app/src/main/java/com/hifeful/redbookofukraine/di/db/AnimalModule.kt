package com.hifeful.redbookofukraine.di.db

import com.hifeful.redbookofukraine.data.db.RedBookDatabase
import com.hifeful.redbookofukraine.data.db.animal.*
import dagger.Module
import dagger.Provides

@Module
class AnimalModule {
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
}