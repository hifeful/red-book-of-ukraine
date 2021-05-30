package com.hifeful.redbookofukraine.data.db.animal

import com.hifeful.redbookofukraine.data.model.animal.toOrganismCategory
import com.hifeful.redbookofukraine.domain.OrganismCategory
import javax.inject.Inject

class AnimalFamilyRepository @Inject constructor(private val animalFamilyDao: AnimalFamilyDao) {
    fun getAllAnimalFamilies(): List<OrganismCategory> =
        animalFamilyDao.getAllAnimalFamilies().map { it.toOrganismCategory() }

    fun getAnimalFamilyById(id: Long): OrganismCategory =
        animalFamilyDao.getAnimalFamilyById(id).toOrganismCategory()
}