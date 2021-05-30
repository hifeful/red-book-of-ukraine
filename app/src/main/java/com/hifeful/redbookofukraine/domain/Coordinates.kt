package com.hifeful.redbookofukraine.domain

import com.google.android.gms.maps.model.LatLng

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)

fun Coordinates.toLatLng(): LatLng = LatLng(latitude, longitude)