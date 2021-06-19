package com.hifeful.redbookofukraine.data.db.plant

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.OrganismSearchEntity
import com.hifeful.redbookofukraine.data.model.plant.PlantEntity

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
    fun getAllPlants(): List<PlantEntity>

    @Query("SELECT * FROM plant WHERE id = :id")
    fun getPlantById(id: Long): PlantEntity

    @Query("SELECT id, name FROM plant WHERE name LIKE :query LIMIT 5")
    fun searchPlantsShortByName(query: String): List<OrganismSearchEntity>

    @Query("SELECT * FROM plant WHERE name LIKE :query")
    fun searchPlantsByName(query: String): List<PlantEntity>
}