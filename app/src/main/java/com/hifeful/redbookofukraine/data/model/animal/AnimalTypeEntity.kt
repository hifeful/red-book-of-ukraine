package com.hifeful.redbookofukraine.data.model.animal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.OrganismCategory

@Entity(tableName = "animal_type")
data class AnimalTypeEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun AnimalTypeEntity.toOrganismCategory() = OrganismCategory(id, name)
