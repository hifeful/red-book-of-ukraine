package com.hifeful.redbookofukraine.data.model.plant

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.OrganismCategory

@Entity(tableName = "plant_family")
data class PlantFamilyEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun PlantFamilyEntity.toOrganismCategory() = OrganismCategory(id, name)