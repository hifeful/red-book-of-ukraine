package com.hifeful.redbookofukraine.data.db.plant

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.plant.PlantFamilyEntity

@Dao
interface PlantFamilyDao {
    @Query("SELECT * FROM plant_family")
    fun getAllPlantFamilies(): List<PlantFamilyEntity>

    @Query("SELECT * FROM plant_family WHERE id = :id")
    fun getPlantFamilyById(id: Long): PlantFamilyEntity
}