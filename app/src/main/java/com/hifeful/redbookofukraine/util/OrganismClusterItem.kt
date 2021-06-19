package com.hifeful.redbookofukraine.util

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.hifeful.redbookofukraine.domain.Coordinates
import com.hifeful.redbookofukraine.domain.Organism
import com.hifeful.redbookofukraine.domain.toLatLng

class OrganismClusterItem(
    private val coordinates: Coordinates,
    val organism: Organism
) : ClusterItem {
    override fun getPosition(): LatLng = coordinates.toLatLng()

    override fun getTitle(): String? = null

    override fun getSnippet(): String? = null
}