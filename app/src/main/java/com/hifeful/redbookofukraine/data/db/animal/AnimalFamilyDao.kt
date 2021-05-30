package com.hifeful.redbookofukraine.data.db.animal

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.animal.AnimalFamilyEntity

@Dao
interface AnimalFamilyDao {
    @Query("SELECT * FROM animal_family")
    fun getAllAnimalFamilies(): List<AnimalFamilyEntity>

    @Query("SELECT * FROM animal_family WHERE id = :id")
    fun getAnimalFamilyById(id: Long): AnimalFamilyEntity
}