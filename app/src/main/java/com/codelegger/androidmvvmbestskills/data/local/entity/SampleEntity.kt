package com.codelegger.androidmvvmbestskills.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Room representation of a cached sample record. */
@Entity(tableName = "samples")
data class SampleEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val body: String,
)
