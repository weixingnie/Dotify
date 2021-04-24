package edu.uw.wn5.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.wn5.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {

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
                currentSong.text = clickedSong.title + " - " + clickedSong.artist
                miniPlayer.setOnClickListener {navigateToPlayerActivity(this@SongListActivity, clickedSong)}
            }
        }
    }
}