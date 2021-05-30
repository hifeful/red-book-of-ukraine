package com.hifeful.redbookofukraine.data.model.plant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_family")
data class PlantFamilyEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)