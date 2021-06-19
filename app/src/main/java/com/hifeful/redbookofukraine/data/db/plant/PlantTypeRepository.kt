package com.hifeful.redbookofukraine.data.db.plant

import com.hifeful.redbookofukraine.data.model.plant.toOrganismCategory
import com.hifeful.redbookofukraine.domain.OrganismCategory
import javax.inject.Inject

class PlantTypeRepository @Inject constructor(private val plantTypeDao: PlantTypeDao) {
    fun getAllPlantTypes(): List<OrganismCategory> =
        plantTypeDao.getAllPlantTypes().map { it.toOrganismCategory() }

    fun getPlantTypeById(id: Long): OrganismCategory =
        plantTypeDao.getPlantTypeById(id).toOrganismCategory()
}