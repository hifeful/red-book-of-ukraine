package com.hifeful.redbookofukraine.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hifeful.redbookofukraine.domain.Organism
import com.hifeful.redbookofukraine.domain.SearchInteractor
import com.hifeful.redbookofukraine.util.surroundWithPercents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchInteractor: SearchInteractor) : ViewModel() {
    private val _searchQuery = MutableLiveData<String?>()
    val searchQuery: LiveData<String?>
        get() = _searchQuery

    private val _searchResults = MutableLiveData<List<Organism>>()
    val searchResults: LiveData<List<Organism>>
        get() = _searchResults


    fun searchOrganisms(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResults = searchInteractor.searchOrganisms(query.surroundWithPercents())
            withContext(Dispatchers.Main) {
                _searchResults.value = searchResults
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getSearchQuery() = _searchQuery.value
}