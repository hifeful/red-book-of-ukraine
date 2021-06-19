package com.hifeful.redbookofukraine.di.db

import com.hifeful.redbookofukraine.data.db.RedBookDatabase
import com.hifeful.redbookofukraine.data.db.plant.*
import dagger.Module
import dagger.Provides

@Module
class PlantModule {
    @Provides
    fun providePlantTypeDao(database: RedBookDatabase): PlantTypeDao = database.plantTypeDao()

    @Provides
    fun providePlantDivisionDao(database: RedBookDatabase): PlantDivisionDao = database.plantDivisionDao()

    @Provides
    fun providePlantFamilyDao(database: RedBookDatabase): PlantFamilyDao = database.plantFamilyDao()

    @Provides
    fun providePlantDao(database: RedBookDatabase): PlantDao = database.plantDao()

    @Provides
    fun providePlantTypeRepository(plantTypeDao: PlantTypeDao): PlantTypeRepository =
        PlantTypeRepository(plantTypeDao)

    @Provides
    fun providePlantDivisionRepository(plantDivisionDao: PlantDivisionDao): PlantDivisionRepository =
        PlantDivisionRepository(plantDivisionDao)

    @Provides
    fun providePlantFamilyRepository(plantFamilyDao: PlantFamilyDao): PlantFamilyRepository =
        PlantFamilyRepository(plantFamilyDao)

    @Provides
    fun providePlantRepository(
        plantDao: PlantDao,
        plantTypeRepository: PlantTypeRepository,
        plantDivisionRepository: PlantDivisionRepository,
        plantFamilyRepository: PlantFamilyRepository
    ): PlantRepository = PlantRepository(
        plantDao,
        plantTypeRepository,
        plantDivisionRepository,
        plantFamilyRepository
    )
}