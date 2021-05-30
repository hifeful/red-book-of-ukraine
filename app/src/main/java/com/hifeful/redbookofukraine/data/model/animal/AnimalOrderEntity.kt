package com.hifeful.redbookofukraine.data.model.animal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.OrganismCategory

@Entity(tableName = "animal_order")
data class AnimalOrderEntity(
    @PrimaryKey
    val id: Long,
    val name: String
)

fun AnimalOrderEntity.toOrganismCategory() = OrganismCategory(id, name)