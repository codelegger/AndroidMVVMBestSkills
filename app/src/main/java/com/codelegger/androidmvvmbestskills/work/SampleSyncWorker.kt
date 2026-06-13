package com.codelegger.androidmvvmbestskills.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.codelegger.androidmvvmbestskills.domain.repository.SampleRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Background worker that refreshes the sample cache. Constructed by Hilt's
 * [androidx.hilt.work.HiltWorkerFactory], so it can inject domain dependencies.
 */
@HiltWorker
class SampleSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: SampleRepository,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = try {
        repository.refresh()
        Result.success()
    } catch (e: Exception) {
        if (runAttemptCount < MAX_RETRIES) Result.retry() else Result.failure()
    }

    companion object {
        const val NAME = "sample_sync_worker"
        private const val MAX_RETRIES = 3
    }
}
