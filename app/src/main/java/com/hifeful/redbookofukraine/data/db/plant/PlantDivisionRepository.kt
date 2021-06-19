package com.hifeful.redbookofukraine.data.db.plant

import com.hifeful.redbookofukraine.data.model.plant.toOrganismCategory
import com.hifeful.redbookofukraine.domain.OrganismCategory
import javax.inject.Inject

class PlantDivisionRepository @Inject constructor(private val plantDivisionDao: PlantDivisionDao) {
    fun getAllPlantDivisions(): List<OrganismCategory> =
        plantDivisionDao.getAllPlantDivisions().map { it.toOrganismCategory() }

    fun getPlantDivisionById(id: Long): OrganismCategory =
        plantDivisionDao.getPlantDivisionById(id).toOrganismCategory()
}