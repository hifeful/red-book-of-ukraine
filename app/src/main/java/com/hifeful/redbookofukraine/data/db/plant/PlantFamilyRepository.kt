package com.hifeful.redbookofukraine.data.db.plant

import com.hifeful.redbookofukraine.data.model.plant.toOrganismCategory
import com.hifeful.redbookofukraine.domain.OrganismCategory
import javax.inject.Inject

class PlantFamilyRepository @Inject constructor(private val plantFamilyDao: PlantFamilyDao) {
    fun getAllPlantFamilies(): List<OrganismCategory> =
        plantFamilyDao.getAllPlantFamilies().map { it.toOrganismCategory() }

    fun getPlantFamilyById(id: Long): OrganismCategory =
        plantFamilyDao.getPlantFamilyById(id).toOrganismCategory()
}