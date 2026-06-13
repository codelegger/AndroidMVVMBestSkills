package com.codelegger.androidmvvmbestskills.data.repository

import com.codelegger.androidmvvmbestskills.data.local.dao.SampleDao
import com.codelegger.androidmvvmbestskills.data.local.entity.SampleEntity
import com.codelegger.androidmvvmbestskills.data.remote.api.SampleApi
import com.codelegger.androidmvvmbestskills.data.remote.dto.SampleDto
import com.codelegger.androidmvvmbestskills.di.IoDispatcher
import com.codelegger.androidmvvmbestskills.domain.model.Sample
import com.codelegger.androidmvvmbestskills.domain.repository.SampleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Offline-first [SampleRepository]: the UI always observes Room, and [refresh]
 * pulls from the network and writes through to the cache.
 */
class SampleRepositoryImpl @Inject constructor(
    private val api: SampleApi,
    private val dao: SampleDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : SampleRepository {

    override fun observeSamples(): Flow<List<Sample>> =
        dao.observeAll().map { entities -> entities.map(SampleEntity::toDomain) }

    override suspend fun refresh() = withContext(ioDispatcher) {
        val remote = api.getSamples()
        dao.upsertAll(remote.map(SampleDto::toEntity))
    }
}

private fun SampleEntity.toDomain() = Sample(id = id, title = title, body = body)

private fun SampleDto.toEntity() = SampleEntity(id = id, title = title, body = body)
