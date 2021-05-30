package com.hifeful.redbookofukraine.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hifeful.redbookofukraine.data.db.organism.OrganismRepository
import com.hifeful.redbookofukraine.domain.Organism
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapViewModel @Inject constructor(private val organismRepository: OrganismRepository) :
    ViewModel() {
    private val TAG = MapViewModel::class.qualifiedName

    private val _organisms = MutableLiveData<List<Organism>>()
    val organisms: LiveData<List<Organism>>
        get() = _organisms

    fun getAllOrganisms() {
        viewModelScope.launch(Dispatchers.IO) {
            val organisms = organismRepository.getAllOrganisms()

            withContext(Dispatchers.Main) {
                _organisms.value = organisms
            }
        }
    }
}