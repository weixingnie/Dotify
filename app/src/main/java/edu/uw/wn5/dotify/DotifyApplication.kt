package edu.uw.wn5.dotify

import android.app.Application
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.wn5.dotify.Data.DataRepository

/*
 *  Capture data repository and songs
 */
class DotifyApplication: Application() {
    lateinit var dataRepository: DataRepository
    lateinit var allSongs: List<Song>

    override fun onCreate() {
        super.onCreate()
        dataRepository = DataRepository()
        allSongs = SongDataProvider.getAllSongs()
    }
}