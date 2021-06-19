package com.hifeful.redbookofukraine.data.db.plant

import com.hifeful.redbookofukraine.data.model.plant.PlantEntity
import com.hifeful.redbookofukraine.data.model.plant.toPlant
import com.hifeful.redbookofukraine.data.model.toOrganismSearch
import com.hifeful.redbookofukraine.domain.Animal
import com.hifeful.redbookofukraine.domain.OrganismType
import com.hifeful.redbookofukraine.domain.Plant
import javax.inject.Inject

class PlantRepository @Inject constructor(
    private val plantDao: PlantDao,
    private val plantTypeRepository: PlantTypeRepository,
    private val plantDivisionRepository: PlantDivisionRepository,
    private val plantFamilyRepository: PlantFamilyRepository
) {
    fun getAllPlants(): List<Plant> {
        val plantEntities = plantDao.getAllPlants()

        val plants = mutableListOf<Plant>()
        for (plantEntity in plantEntities) {
            plants.add(getPlantWithCategories(plantEntity))
        }
        return plants
    }

    fun getPlantById(id: Long): Plant {
        val plantEntity = plantDao.getPlantById(id)

        return getPlantWithCategories(plantEntity)
    }

    private fun getPlantWithCategories(plantEntity: PlantEntity): Plant {
        plantEntity.run {
            val plantType = plantTypeRepository.getPlantTypeById(type)
            val plantDivision = plantDivisionRepository.getPlantDivisionById(division)
            val plantFamily = plantFamilyRepository.getPlantFamilyById(family)

            return toPlant(plantType, plantDivision, plantFamily)
        }
    }

    fun searchPlantsShortByName(query: String) =
        plantDao.searchPlantsShortByName(query).map { it.toOrganismSearch(OrganismType.PLANT) }

    fun searchPlantsByName(query: String): List<Plant> {
        val plantEntities = plantDao.searchPlantsByName(query)

        val plants = mutableListOf<Plant>()
        for (plantEntity in plantEntities) {
            plants.add(getPlantWithCategories(plantEntity))
        }
        return plants
    }
}