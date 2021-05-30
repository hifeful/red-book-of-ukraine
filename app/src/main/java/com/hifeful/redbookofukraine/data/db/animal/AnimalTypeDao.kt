package com.hifeful.redbookofukraine.data.db.animal

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.animal.AnimalTypeEntity

@Dao
interface AnimalTypeDao {
    @Query("SELECT * FROM animal_type")
    fun getAllAnimalTypes(): List<AnimalTypeEntity>

    @Query("SELECT * FROM animal_type WHERE id = :id")
    fun getAnimalTypeById(id: Long): AnimalTypeEntity
}