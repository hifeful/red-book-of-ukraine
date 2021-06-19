package com.hifeful.redbookofukraine.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hifeful.redbookofukraine.data.db.organism.OrganismRepository
import com.hifeful.redbookofukraine.domain.Organism
import com.hifeful.redbookofukraine.domain.OrganismSearch
import com.hifeful.redbookofukraine.domain.SearchInteractor
import com.hifeful.redbookofukraine.util.surroundWithPercents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val organismRepository: OrganismRepository,
    private val searchInteractor: SearchInteractor
) :
    ViewModel() {

    init {
        getAllOrganisms()
    }

    private val TAG = MapViewModel::class.qualifiedName

    private val _organisms = MutableLiveData<List<Organism>>()
    val organisms: LiveData<List<Organism>>
        get() = _organisms

    private val _isSearchVisible = MutableLiveData(false)
    val isSearchVisible: LiveData<Boolean>
        get() = _isSearchVisible

    private val _searchSuggestions = MutableLiveData<List<OrganismSearch>>()
    val searchSuggestions: LiveData<List<OrganismSearch>>
        get() = _searchSuggestions

    var searchQuery: String = ""

    private fun getAllOrganisms() {
        viewModelScope.launch(Dispatchers.IO) {
            val organisms = organismRepository.getAllOrganisms()

            withContext(Dispatchers.Main) {
                _organisms.value = organisms
            }
        }
    }

    fun setSearchVisible(isVisible: Boolean) {
        _isSearchVisible.value = isVisible
    }

    fun searchOrganismsShort(query: String) {
        searchQuery = query
        viewModelScope.launch(Dispatchers.IO) {
            val suggestions = searchInteractor.searchOrganismsShort(query.surroundWithPercents())
            withContext(Dispatchers.Main) {
                _searchSuggestions.value = suggestions
            }
        }
    }
}