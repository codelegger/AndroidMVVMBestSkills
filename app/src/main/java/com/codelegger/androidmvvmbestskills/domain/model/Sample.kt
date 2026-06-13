package com.codelegger.androidmvvmbestskills.domain.model

/** Domain model exposed to the presentation layer, decoupled from storage/network types. */
data class Sample(
    val id: Int,
    val title: String,
    val body: String,
)
