package com.hifeful.redbookofukraine.data.model.plant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.OrganismCategory
import com.hifeful.redbookofukraine.domain.Plant

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
    val family: Long,
    val status: String,
    val areal: String,
    val population: String?,
    @ColumnInfo(name = "cause_of_extinction")
    val causeOfExtinction: String,
    @ColumnInfo(name = "scientific_value")
    val scientificValue: String?,
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

fun PlantEntity.toPlant(
    plantType: OrganismCategory,
    plantDivision: OrganismCategory,
    plantFamily: OrganismCategory,
) = Plant(
    id = id,
    coordinates = mutableListOf(),
    name = name,
    nameLatin = nameLatin,
    photoUrl = photoUrl,
    status = status,
    areal = areal,
    population = population,
    causeOfExtinction = causeOfExtinction,
    scientificValue = scientificValue,
    breeding = breeding,
    securityMeasures = securityMeasures,
    commercialValue = commercialValue,
    sources = sources,
    plantType = plantType,
    plantDivision = plantDivision,
    plantFamily = plantFamily,
    growingConditions = growingConditions,
    biomorphologicalCharacteristics = biomorphologicalCharacteristics
)