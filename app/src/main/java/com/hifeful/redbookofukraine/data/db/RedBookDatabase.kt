package com.hifeful.redbookofukraine.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hifeful.redbookofukraine.data.db.animal.*
import com.hifeful.redbookofukraine.data.db.organism.OrganismDao
import com.hifeful.redbookofukraine.data.db.plant.PlantDao
import com.hifeful.redbookofukraine.data.db.plant.PlantDivisionDao
import com.hifeful.redbookofukraine.data.db.plant.PlantFamilyDao
import com.hifeful.redbookofukraine.data.db.plant.PlantTypeDao
import com.hifeful.redbookofukraine.data.model.OrganismEntity
import com.hifeful.redbookofukraine.data.model.animal.*
import com.hifeful.redbookofukraine.data.model.plant.PlantEntity
import com.hifeful.redbookofukraine.data.model.plant.PlantDivisionEntity
import com.hifeful.redbookofukraine.data.model.plant.PlantFamilyEntity
import com.hifeful.redbookofukraine.data.model.plant.PlantTypeEntity
import com.hifeful.redbookofukraine.domain.Organism

@Database(entities = [
    OrganismEntity::class,
    AnimalEntity::class,
    AnimalClassEntity::class,
    AnimalFamilyEntity::class,
    AnimalOrderEntity::class,
    AnimalTypeEntity::class,
    PlantEntity::class,
    PlantDivisionEntity::class,
    PlantFamilyEntity::class,
    PlantTypeEntity::class],
    version = 1)
abstract class RedBookDatabase : RoomDatabase() {
    abstract fun animalTypeDao(): AnimalTypeDao
    abstract fun animalClassDao(): AnimalClassDao
    abstract fun animalOrderDao(): AnimalOrderDao
    abstract fun animalFamilyDao(): AnimalFamilyDao

    abstract fun plantTypeDao(): PlantTypeDao
    abstract fun plantDivisionDao(): PlantDivisionDao
    abstract fun plantFamilyDao(): PlantFamilyDao

    abstract fun organismDao(): OrganismDao
    abstract fun animalDao(): AnimalDao
    abstract fun plantDao(): PlantDao

    companion object {
        private const val DATABASE_NAME = "red_book_of_ukraine.db"
        private var instance: RedBookDatabase? = null

        fun getInstance(application: Application): RedBookDatabase {
            return instance ?: Room.databaseBuilder(application,
                RedBookDatabase::class.java,
                DATABASE_NAME)
                .createFromAsset(DATABASE_NAME)
                .build()
        }
    }
}