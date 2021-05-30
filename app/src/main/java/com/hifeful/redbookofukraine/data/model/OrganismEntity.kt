package com.hifeful.redbookofukraine.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organism")
data class OrganismEntity(
    @PrimaryKey
    val id: Long,
    @Embedded
    val organismShort: OrganismShort,
    val latitude: Double,
    val longitude: Double
)