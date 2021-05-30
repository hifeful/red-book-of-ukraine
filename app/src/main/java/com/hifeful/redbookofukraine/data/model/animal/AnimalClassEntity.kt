package com.hifeful.redbookofukraine.data.model.animal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.OrganismCategory

@Entity(tableName = "animal_class")
data class AnimalClassEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun AnimalClassEntity.toOrganismCategory() = OrganismCategory(id, name)