package com.hifeful.redbookofukraine.data.db.animal

import com.hifeful.redbookofukraine.data.model.animal.toOrganismCategory
import com.hifeful.redbookofukraine.domain.OrganismCategory
import javax.inject.Inject

class AnimalClassRepository @Inject constructor(private val animalClassDao: AnimalClassDao) {
    fun getAllAnimalTypes(): List<OrganismCategory> =
        animalClassDao.getAllAnimalClasses().map { it.toOrganismCategory() }

    fun getAnimalClassById(id: Long): OrganismCategory =
        animalClassDao.getAnimalClassById(id).toOrganismCategory()
}