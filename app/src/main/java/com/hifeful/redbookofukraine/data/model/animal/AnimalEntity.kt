package com.hifeful.redbookofukraine.data.model.animal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hifeful.redbookofukraine.domain.Animal
import com.hifeful.redbookofukraine.domain.Coordinates
import com.hifeful.redbookofukraine.domain.OrganismCategory

@Entity(tableName = "animal")
data class AnimalEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    @ColumnInfo(name = "name_latin")
    val nameLatin: String,
    @ColumnInfo(name = "photo_url")
    val photoUrl: String,
    val type: Long,
    @ColumnInfo(name = "class")
    val animal_class: Long,
    val order: Long,
    val family: Long?,
    val status: String,
    val areal: String,
    val population: String,
    @ColumnInfo(name = "cause_of_extinction")
    val causeOfExtinction: String,
    @ColumnInfo(name = "scientific_value")
    val scientificValue: String,
    @ColumnInfo(name = "morphological_features")
    val morphologicalFeatures: String,
    @ColumnInfo(name = "security_measures")
    val securityMeasures: String?,
    val breeding: String?,
    @ColumnInfo(name = "commercial_value")
    val commercialValue: String?,
    val sources: String
)

fun AnimalEntity.toAnimal(
    animalType: OrganismCategory,
    animalClass: OrganismCategory,
    animalOrder: OrganismCategory,
    animalFamily: OrganismCategory?
) = Animal(
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
    animalType = animalType,
    animalClass = animalClass,
    animalOrder = animalOrder,
    animalFamily = animalFamily,
    morphologicalFeatures = morphologicalFeatures
)