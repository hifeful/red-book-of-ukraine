package com.hifeful.redbookofukraine.data.db.animal

import com.hifeful.redbookofukraine.data.model.animal.AnimalEntity
import com.hifeful.redbookofukraine.data.model.animal.toAnimal
import com.hifeful.redbookofukraine.data.model.toOrganismSearch
import com.hifeful.redbookofukraine.domain.Animal
import com.hifeful.redbookofukraine.domain.OrganismType
import javax.inject.Inject

class AnimalRepository @Inject constructor(
    private val animalDao: AnimalDao,
    private val animalTypeRepository: AnimalTypeRepository,
    private val animalClassRepository: AnimalClassRepository,
    private val animalOrderRepository: AnimalOrderRepository,
    private val animalFamilyRepository: AnimalFamilyRepository
) {
    fun getAllAnimals(): List<Animal> {
        val animalEntities = animalDao.getAllAnimals()

        val animals = mutableListOf<Animal>()
        for (animalEntity in animalEntities) {
            animals.add(getAnimalWithCategories(animalEntity))
        }
        return animals
    }

    fun getAnimalById(id: Long): Animal {
        val animalEntity = animalDao.getAnimalById(id)

        return getAnimalWithCategories(animalEntity)
    }

    private fun getAnimalWithCategories(animalEntity: AnimalEntity): Animal {
        animalEntity.run {
            val animalType = animalTypeRepository.getAnimalTypeById(type)
            val animalClass = animalClassRepository.getAnimalClassById(animal_class)
            val animalOrder = animalOrderRepository.getAnimalOrderById(order)
            val animalFamily = family?.let {
                animalFamilyRepository.getAnimalFamilyById(it)
            }

            return toAnimal(animalType, animalClass, animalOrder, animalFamily)
        }
    }

    fun searchAnimalsShortByName(query: String) =
        animalDao.searchAnimalsShortByName(query).map { it.toOrganismSearch(OrganismType.ANIMAL) }

    fun searchAnimalsByName(query: String): List<Animal> {
        val animalEntities = animalDao.searchAnimalsByName(query)

        val animals = mutableListOf<Animal>()
        for (animalEntity in animalEntities) {
            animals.add(getAnimalWithCategories(animalEntity))
        }
        return animals
    }
}