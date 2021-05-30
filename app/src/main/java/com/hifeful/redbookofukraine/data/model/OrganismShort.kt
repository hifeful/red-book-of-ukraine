package com.hifeful.redbookofukraine.data.model

import androidx.room.ColumnInfo

data class OrganismShort(
    @ColumnInfo(name = "organism_id")
    val organismId: Long,
    @ColumnInfo(name = "organism_type")
    val organismType: String
)