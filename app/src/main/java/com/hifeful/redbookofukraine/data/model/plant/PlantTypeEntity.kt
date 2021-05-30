package com.hifeful.redbookofukraine.data.model.plant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_type")
data class PlantTypeEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)