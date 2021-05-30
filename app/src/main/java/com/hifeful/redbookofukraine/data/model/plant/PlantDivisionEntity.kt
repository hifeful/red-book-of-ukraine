package com.hifeful.redbookofukraine.data.model.plant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_division")
data class PlantDivisionEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)