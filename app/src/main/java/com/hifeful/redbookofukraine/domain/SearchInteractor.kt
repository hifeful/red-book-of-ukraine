package com.hifeful.redbookofukraine.domain

import com.hifeful.redbookofukraine.data.db.animal.AnimalRepository
import com.hifeful.redbookofukraine.data.db.plant.PlantRepository
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val animalRepository: AnimalRepository,
    private val plantRepository: PlantRepository
) {
    fun searchOrganismsShort(query: String): List<OrganismSearch> {
        val organismsShort = animalRepository.searchAnimalsShortByName(query) +
                plantRepository.searchPlantsShortByName(query)

        return organismsShort.sortedBy { it.name }
    }

    fun searchOrganisms(query: String): List<Organism> {
        val organisms = animalRepository.searchAnimalsByName(query) +
                plantRepository.searchPlantsByName(query)

        return organisms.sortedBy { it.name }
    }
}