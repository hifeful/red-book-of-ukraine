package com.hifeful.redbookofukraine.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrganismCategory(
    val id: Long,
    val name: String
) : Parcelable
