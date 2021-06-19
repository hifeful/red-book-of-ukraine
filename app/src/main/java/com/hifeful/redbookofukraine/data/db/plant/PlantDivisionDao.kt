package com.hifeful.redbookofukraine.data.db.plant

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.plant.PlantDivisionEntity

@Dao
interface PlantDivisionDao {
    @Query("SELECT * FROM plant_division")
    fun getAllPlantDivisions(): List<PlantDivisionEntity>

    @Query("SELECT * FROM plant_division WHERE id = :id")
    fun getPlantDivisionById(id: Long): PlantDivisionEntity
}