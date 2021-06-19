package com.hifeful.redbookofukraine.domain

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    val latitude: Double,
    val longitude: Double
) : Parcelable

fun Coordinates.toLatLng(): LatLng = LatLng(latitude, longitude)