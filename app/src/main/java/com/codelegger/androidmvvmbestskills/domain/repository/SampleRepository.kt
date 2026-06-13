package com.codelegger.androidmvvmbestskills.domain.repository

import com.codelegger.androidmvvmbestskills.domain.model.Sample
import kotlinx.coroutines.flow.Flow

/**
 * Single source of truth for samples. The presentation layer depends on this
 * abstraction; the data layer provides the implementation.
 */
interface SampleRepository {

    /** Observes the locally cached samples. */
    fun observeSamples(): Flow<List<Sample>>

    /** Fetches the latest samples from the network and updates the local cache. */
    suspend fun refresh()
}
