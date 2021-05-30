package com.hifeful.redbookofukraine.data.db.organism

import com.hifeful.redbookofukraine.data.db.animal.AnimalRepository
import com.hifeful.redbookofukraine.domain.Coordinates
import com.hifeful.redbookofukraine.domain.Organism
import java.lang.Exception
import javax.inject.Inject

class OrganismRepository @Inject constructor(
    private val organismDao: OrganismDao,
    private val animalRepository: AnimalRepository
) {
    fun getAllOrganisms(): List<Organism> {
        val organismEntities = organismDao.getAllOrganismDistinct()

        val organisms = mutableListOf<Organism>()
        for (organismEntity in organismEntities) {
            organismEntity.run {
                val organism = getOrganism(organismType, organismId)
                val organismLatitudes = organismDao.getAllOrganismLatitudeById(organismId)
                val organismLongitudes = organismDao.getAllOrganismLongitudeById(organismId)

                for (i in organismLatitudes.indices) {
                    organism.coordinates.add(Coordinates(organismLatitudes[i], organismLongitudes[i]))
                }
                organisms.add(organism)
            }
        }
        return organisms
    }

    private fun getOrganism(organismType: String, organismId: Long): Organism =
        when (organismType) {
            "animal" -> animalRepository.getAnimalById(organismId)
            else -> throw Exception("Unsupported organism type")
        }
}