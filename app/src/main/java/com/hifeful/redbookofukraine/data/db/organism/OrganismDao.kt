package com.hifeful.redbookofukraine.data.db.organism

import androidx.room.Dao
import androidx.room.Query
import com.hifeful.redbookofukraine.data.model.OrganismShort

@Dao
interface OrganismDao {
    @Query("SELECT DISTINCT organism_id, organism_type FROM organism")
    fun getAllOrganismDistinct(): List<OrganismShort>

    @Query("SELECT latitude FROM organism WHERE organism_id = :id AND organism_type = :organismType")
    fun getAllOrganismLatitudeById(id: Long, organismType: String): List<Double>

    @Query("SELECT longitude FROM organism WHERE organism_id = :id AND organism_type = :organismType")
    fun getAllOrganismLongitudeById(id: Long, organismType: String): List<Double>
}