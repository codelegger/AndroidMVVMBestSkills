package com.codelegger.androidmvvmbestskills.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codelegger.androidmvvmbestskills.data.local.dao.SampleDao
import com.codelegger.androidmvvmbestskills.data.local.entity.SampleEntity

@Database(
    entities = [SampleEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao

    companion object {
        const val NAME = "android_mvvm_best_skills.db"
    }
}
