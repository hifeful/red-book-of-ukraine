package com.hifeful.redbookofukraine.data.db.animal

import com.hifeful.redbookofukraine.data.model.animal.toOrganismCategory
import com.hifeful.redbookofukraine.domain.OrganismCategory
import javax.inject.Inject

class AnimalTypeRepository @Inject constructor(private val animalTypeDao: AnimalTypeDao) {
    fun getAllAnimalTypes(): List<OrganismCategory> =
        animalTypeDao.getAllAnimalTypes().map { it.toOrganismCategory() }

    fun getAnimalTypeById(id: Long): OrganismCategory =
        animalTypeDao.getAnimalTypeById(id).toOrganismCategory()
}