package edu.uw.wn5.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.wn5.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {

    private val RECORD = "record"
    private lateinit var temp: Song
    /*
     *  Any clicked song would be provide a further details on the miniplayer
     *  It is the main page for all the recycle view. User can scroll down as
     *  fast and as much as they can.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        var music = SongDataProvider.getAllSongs()
        val rvMusic = binding.rvMusic


        with (binding) {
            if (savedInstanceState != null) {
                temp = savedInstanceState.getParcelable(RECORD) ?: return
                miniPlayer.visibility = View.VISIBLE
                currentSong.text = temp.title + " - " + temp.artist
                miniPlayer.setOnClickListener {navigateToPlayerActivity(this@SongListActivity, temp)}
            }
            title = "All songs"
            val adapter = SongListAdapter(music)
            rvMusic.adapter = adapter
            shuffleButton.setOnClickListener {
                music = music.shuffled()
                rvMusic.adapter = adapter.apply { updateSong(music) }
            }
            adapter.onSongClickListener = {
                Toast.makeText(this@SongListActivity, "You clicked a song.", Toast.LENGTH_SHORT).show()
            }
            adapter.onSongClickListener = { clickedSong: Song ->
                miniPlayer.visibility = View.VISIBLE
                temp = clickedSong
                currentSong.text = clickedSong.title + " - " + clickedSong.artist
                miniPlayer.setOnClickListener {navigateToPlayerActivity(this@SongListActivity, clickedSong)}
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(RECORD, temp)
        super.onSaveInstanceState(outState)
    }
}