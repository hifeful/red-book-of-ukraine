package com.hifeful.redbookofukraine.data.db.animal

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.animal.AnimalOrderEntity

@Dao
interface AnimalOrderDao {
    @Query("SELECT * FROM animal_order")
    fun getAllAnimalOrders(): List<AnimalOrderEntity>

    @Query("SELECT * FROM animal_order WHERE id = :id")
    fun getAnimalOrderById(id: Long): AnimalOrderEntity
}