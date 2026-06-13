package com.codelegger.androidmvvmbestskills.data.remote.api

import com.codelegger.androidmvvmbestskills.data.remote.dto.SampleDto
import retrofit2.http.GET

/** Retrofit endpoint definition for sample data. */
interface SampleApi {

    @GET("posts")
    suspend fun getSamples(): List<SampleDto>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
