package com.hifeful.redbookofukraine.data.model.plant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant")
data class PlantEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    @ColumnInfo(name = "name_latin")
    val nameLatin: String,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String,
    val type: Long,
    val division: Long,
    val family: Long?,
    val status: String,
    val areal: String,
    val population: String,
    @ColumnInfo(name = "cause_of_extinction")
    val causeOfExtinction: String,
    @ColumnInfo(name = "scientific_value")
    val scientificValue: String,
    @ColumnInfo(name = "growing_conditions")
    val growingConditions: String,
    @ColumnInfo(name = "biomorphological_characteristics")
    val biomorphologicalCharacteristics: String,
    @ColumnInfo(name = "security_measures")
    val securityMeasures: String?,
    val breeding: String?,
    @ColumnInfo(name =  "commercial_value")
    val commercialValue: String?,
    val sources: String
)