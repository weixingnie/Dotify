package edu.uw.wn5.dotify.manager
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.wn5.dotify.DotifyApplication

class MusicSyncWorker (
        context: Context,
        workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val application by lazy { context.applicationContext as DotifyApplication }
    private val notificationManager by lazy { application.notificationManager}

    override suspend fun doWork(): Result {
        val song = SongDataProvider.getAllSongs().random()
        notificationManager.publishNewMusicNotification(song)
        return Result.success()
    }
}