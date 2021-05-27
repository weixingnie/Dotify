package edu.uw.wn5.dotify.manager

import android.content.Context
import android.net.Network
import androidx.work.*
import java.util.concurrent.TimeUnit

private const val MUSIC_SYNC_TAG = "MUSIC_SYNC_TAG"

class MusicSyncManager(context: Context) {

    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun syncMusicPeriodically() {
        if (isMusicSyncRunning()) {
            return
        }


        val request = PeriodicWorkRequestBuilder<MusicSyncWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(
                        Constraints.Builder()
                                .setRequiredNetworkType(NetworkType.CONNECTED)
                                .build()
                )
                .addTag((MUSIC_SYNC_TAG))
                .build()

        workManager.enqueue(request)
    }

    fun stopSync() {
        workManager.cancelAllWorkByTag(MUSIC_SYNC_TAG)
    }

    private fun isMusicSyncRunning(): Boolean {
        return workManager.getWorkInfosByTag(MUSIC_SYNC_TAG).get().any{
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }
}
