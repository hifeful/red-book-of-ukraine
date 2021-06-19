package com.hifeful.redbookofukraine.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Organism(
    open val id: Long,
    open var coordinates: List<Coordinates>,
    open val name: String,
    open val nameLatin: String,
    open val photoUrl: String,
    open val status: String,
    open val areal: String,
    open val population: String?,
    open val causeOfExtinction: String?,
    open val scientificValue: String?,
    open val securityMeasures: String?,
    open val breeding: String?,
    open val commercialValue: String?,
    open val sources: String
) : Parcelable

@Parcelize
data class Animal(
    override val id: Long,
    override var coordinates: List<Coordinates>,
    override val name: String,
    override val nameLatin: String,
    override val photoUrl: String,
    override val status: String,
    override val areal: String,
    override val population: String?,
    override val causeOfExtinction: String?,
    override val scientificValue: String?,
    override val securityMeasures: String?,
    override val breeding: String?,
    override val commercialValue: String?,
    override val sources: String,
    val animalType: OrganismCategory,
    val animalClass: OrganismCategory,
    val animalOrder: OrganismCategory,
    val animalFamily: OrganismCategory?,
    val morphologicalFeatures: String,
) : Organism(
    id,
    coordinates,
    name,
    nameLatin,
    photoUrl,
    status,
    areal,
    population,
    causeOfExtinction,
    scientificValue,
    securityMeasures,
    breeding,
    commercialValue,
    sources
)

@Parcelize
data class Plant(
    override val id: Long,
    override var coordinates: List<Coordinates>,
    override val name: String,
    override val nameLatin: String,
    override val photoUrl: String,
    override val status: String,
    override val areal: String,
    override val population: String?,
    override val causeOfExtinction: String?,
    override val scientificValue: String?,
    override val securityMeasures: String?,
    override val breeding: String?,
    override val commercialValue: String?,
    override val sources: String,
    val plantType: OrganismCategory,
    val plantDivision: OrganismCategory,
    val plantFamily: OrganismCategory,
    val growingConditions: String,
    val biomorphologicalCharacteristics: String
) : Organism(
    id,
    coordinates,
    name,
    nameLatin,
    photoUrl,
    status,
    areal,
    population,
    causeOfExtinction,
    scientificValue,
    securityMeasures,
    breeding,
    commercialValue,
    sources
)