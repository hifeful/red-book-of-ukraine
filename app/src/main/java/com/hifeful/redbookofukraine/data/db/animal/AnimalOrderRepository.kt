package com.hifeful.redbookofukraine.data.db.animal

import com.hifeful.redbookofukraine.data.model.animal.toOrganismCategory
import com.hifeful.redbookofukraine.domain.OrganismCategory
import javax.inject.Inject

class AnimalOrderRepository @Inject constructor(private val animalOrderDao: AnimalOrderDao) {
    fun getAllAnimalOrders(): List<OrganismCategory> =
        animalOrderDao.getAllAnimalOrders().map { it.toOrganismCategory() }

    fun getAnimalOrderById(id: Long): OrganismCategory =
        animalOrderDao.getAnimalOrderById(id).toOrganismCategory()
}
