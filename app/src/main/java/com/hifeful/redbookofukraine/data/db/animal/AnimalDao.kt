package com.hifeful.redbookofukraine.data.db.animal

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.animal.AnimalEntity
import com.hifeful.redbookofukraine.data.model.OrganismSearchEntity

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal")
    fun getAllAnimals(): List<AnimalEntity>

    @Query("SELECT * FROM animal WHERE id = :id")
    fun getAnimalById(id: Long): AnimalEntity

    @Query("SELECT id, name FROM animal WHERE name LIKE :query LIMIT 5")
    fun searchAnimalsShortByName(query: String): List<OrganismSearchEntity>

    @Query("SELECT * FROM animal WHERE name LIKE :query")
    fun searchAnimalsByName(query: String): List<AnimalEntity>
}