package com.hifeful.redbookofukraine.data.db.animal

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.animal.AnimalClassEntity

@Dao
interface AnimalClassDao {
    @Query("SELECT * FROM animal_class")
    fun getAllAnimalClasses(): List<AnimalClassEntity>

    @Query("SELECT * FROM animal_class WHERE id = :id")
    fun getAnimalClassById(id: Long): AnimalClassEntity
}