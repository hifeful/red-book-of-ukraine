package com.hifeful.redbookofukraine.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.hifeful.redbookofukraine.databinding.FragmentOrganismBinding
import com.hifeful.redbookofukraine.domain.Animal
import com.hifeful.redbookofukraine.domain.Organism
import com.hifeful.redbookofukraine.domain.Plant
import dagger.android.support.DaggerFragment

class OrganismFragment : DaggerFragment() {
    private val TAG = OrganismFragment::class.qualifiedName

    private var _binding: FragmentOrganismBinding? = null
    private val binding get() = _binding!!
    private val mArgs: OrganismFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrganismBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOrganismInfo()
    }

    private fun setUpOrganismInfo() {
        val resId = requireContext().resources.getIdentifier(
            mArgs.organism.photoUrl,
            "drawable",
            requireContext().packageName
        )
        binding.organismPhoto.setImageResource(resId)

        binding.organismName.text = mArgs.organism.name
        binding.organismNameLatin.text = mArgs.organism.nameLatin
        setUpSeparateOrganism(mArgs.organism)
    }

    private fun setUpSeparateOrganism(organism: Organism) {
        when (organism) {
            is Animal -> setUpAnimalInfo(organism)
            is Plant -> setUpPlantInfo(organism)
        }
    }

    private fun setUpAnimalInfo(animal: Animal) {
        setUpAnimalCategories(animal)
        setUpAnimalDetails(animal)
    }

    private fun setUpAnimalCategories(animal: Animal) {
        binding.organismCategories.apply {
            organismType.text = animal.animalType.name
            organismClass.text = animal.animalClass.name
            organismOrder.text = animal.animalOrder.name
            animal.animalFamily?.let {
                organismFamily.visibility = View.VISIBLE
                organismFamily.text = it.name
            }
        }
    }

    private fun setUpOrganismDetails(organism: Organism) {
        binding.organismDetails.apply {
            organismStatus.setContentText(organism.status)
            organismAreal.setContentText(organism.areal)
            organism.population?.let {
                organismPopulation.visibility = View.VISIBLE
                organismPopulation.setContentText(it)
            }
            organism.causeOfExtinction?.let {
                organismCauseOfExtinction.visibility = View.VISIBLE
                organismCauseOfExtinction.setContentText(it)
            }
            organism.scientificValue?.let {
                organismScientificValue.visibility = View.VISIBLE
                organismScientificValue.setContentText(it)
            }
            organism.securityMeasures?.let {
                organismSecurityMeasures.visibility = View.VISIBLE
                organismSecurityMeasures.setContentText(it)
            }
            organism.breeding?.let {
                organismBreeding.visibility = View.VISIBLE
                organismBreeding.setContentText(it)
            }
            organism.commercialValue?.let {
                organismCommercialValue.visibility = View.VISIBLE
                organismCommercialValue.setContentText(it)
            }
            organismSources.setContentText(organism.sources)
        }
    }

    private fun setUpAnimalDetails(animal: Animal) {
        setUpOrganismDetails(animal)
        binding.organismDetails.apply {
            animalMorphologicalFeatures.visibility = View.VISIBLE
            animalMorphologicalFeatures.setContentText(animal.morphologicalFeatures)
        }
    }

    private fun setUpPlantInfo(plant: Plant) {
        setUpPlantCategories(plant)
        setUpPlantDetails(plant)
    }

    private fun setUpPlantCategories(plant: Plant) {
        binding.organismCategories.apply {
            organismType.text = plant.plantType.name
            organismClass.text = plant.plantDivision.name
            organismOrder.text = plant.plantFamily.name
        }
    }

    private fun setUpPlantDetails(plant: Plant) {
        setUpOrganismDetails(plant)
        binding.organismDetails.apply {
            plantGrowingConditions.visibility = View.VISIBLE
            plantGrowingConditions.setContentText(plant.growingConditions)

            plantBiomorphologicalCharacteristics.visibility = View.VISIBLE
            plantBiomorphologicalCharacteristics.setContentText(plant.biomorphologicalCharacteristics)
        }
    }
}