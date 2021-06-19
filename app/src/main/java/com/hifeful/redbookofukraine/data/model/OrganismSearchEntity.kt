package com.hifeful.redbookofukraine.data.model

import com.hifeful.redbookofukraine.domain.OrganismSearch
import com.hifeful.redbookofukraine.domain.OrganismType

data class OrganismSearchEntity(
    val id: Int,
    val name: String
)

fun OrganismSearchEntity.toOrganismSearch(organismType: OrganismType) =
    OrganismSearch(id, name, organismType)