package com.hifeful.redbookofukraine.data.db.plant

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.plant.PlantTypeEntity

@Dao
interface PlantTypeDao {
    @Query("SELECT * FROM plant_type")
    fun getAllPlantTypes(): List<PlantTypeEntity>

    @Query("SELECT * FROM plant_type WHERE id = :id")
    fun getPlantTypeById(id: Long): PlantTypeEntity
}