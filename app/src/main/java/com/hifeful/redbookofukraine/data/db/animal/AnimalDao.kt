package com.hifeful.redbookofukraine.data.db.animal

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.animal.AnimalEntity

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal")
    fun getAllAnimals(): List<AnimalEntity>

    @Query("SELECT * FROM animal WHERE id = :id")
    fun getAnimalById(id: Long): AnimalEntity
}