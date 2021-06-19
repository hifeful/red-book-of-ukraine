package com.hifeful.redbookofukraine.data.db.organism

import com.hifeful.redbookofukraine.data.db.animal.AnimalRepository
import com.hifeful.redbookofukraine.data.db.plant.PlantRepository
import com.hifeful.redbookofukraine.domain.Coordinates
import com.hifeful.redbookofukraine.domain.Organism
import java.lang.Exception
import javax.inject.Inject

class OrganismRepository @Inject constructor(
    private val organismDao: OrganismDao,
    private val animalRepository: AnimalRepository,
    private val plantRepository: PlantRepository
) {
    fun getAllOrganisms(): List<Organism> {
        val organismEntities = organismDao.getAllOrganismDistinct()

        val organisms = mutableListOf<Organism>()
        for (organismEntity in organismEntities) {
            organismEntity.run {
                val organism = getOrganism(organismType, organismId)
                val organismLatitudes = organismDao.getAllOrganismLatitudeById(organismId, organismType)
                val organismLongitudes = organismDao.getAllOrganismLongitudeById(organismId, organismType)

                val coordinates = mutableListOf<Coordinates>()
                for (i in organismLatitudes.indices) {
                    coordinates.add(Coordinates(organismLatitudes[i], organismLongitudes[i]))
                }
                organism.coordinates = coordinates
                organisms.add(organism)
            }
        }
        return organisms
    }

    private fun getOrganism(organismType: String, organismId: Long): Organism =
        when (organismType) {
            "animal" -> animalRepository.getAnimalById(organismId)
            "plant" -> plantRepository.getPlantById(organismId)
            else -> throw Exception("Unsupported organism type")
        }
}