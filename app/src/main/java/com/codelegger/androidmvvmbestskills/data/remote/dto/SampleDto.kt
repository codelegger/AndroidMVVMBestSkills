package com.codelegger.androidmvvmbestskills.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/** Network representation of a sample, decoded by Moshi's generated adapter. */
@JsonClass(generateAdapter = true)
data class SampleDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String,
)
