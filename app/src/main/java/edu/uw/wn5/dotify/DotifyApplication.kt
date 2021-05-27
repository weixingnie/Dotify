package edu.uw.wn5.dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.wn5.dotify.Data.DataRepository
import edu.uw.wn5.dotify.manager.MusicNotificationManager
import edu.uw.wn5.dotify.manager.MusicSyncManager

/*
 *  Capture data repository and songs
 */
class DotifyApplication: Application() {
    lateinit var dataRepository: DataRepository
    lateinit var allSongs: List<Song>
    lateinit var musicSyncManager: MusicSyncManager
    lateinit var notificationManager: MusicNotificationManager

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
        allSongs = SongDataProvider.getAllSongs()
        this.musicSyncManager = MusicSyncManager(this)
        this.notificationManager = MusicNotificationManager(this)
    }
}