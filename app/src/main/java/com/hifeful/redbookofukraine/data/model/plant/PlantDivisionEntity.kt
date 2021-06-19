package com.hifeful.redbookofukraine.data.model.plant

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.OrganismCategory

@Entity(tableName = "plant_division")
data class PlantDivisionEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun PlantDivisionEntity.toOrganismCategory() = OrganismCategory(id, name)