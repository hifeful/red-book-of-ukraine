package com.hifeful.redbookofukraine.data.model.animal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.OrganismCategory

@Entity(tableName = "animal_family")
data class AnimalFamilyEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun AnimalFamilyEntity.toOrganismCategory() = OrganismCategory(id, name)