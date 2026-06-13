package com.codelegger.androidmvvmbestskills.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.codelegger.androidmvvmbestskills.data.local.entity.SampleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDao {

    /** Observes all cached samples; emits a new list whenever the table changes. */
    @Query("SELECT * FROM samples ORDER BY id ASC")
    fun observeAll(): Flow<List<SampleEntity>>

    @Upsert
    suspend fun upsertAll(samples: List<SampleEntity>)

    @Query("DELETE FROM samples")
    suspend fun clear()
}
