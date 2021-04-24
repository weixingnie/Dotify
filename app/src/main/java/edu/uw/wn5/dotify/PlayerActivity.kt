package edu.uw.wn5.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.ericchee.songdataprovider.Song
import org.w3c.dom.Text
import kotlin.random.Random
import android.view.MenuItem
import android.widget.*
import edu.uw.wn5.dotify.databinding.ActivityPlayerBinding
import edu.uw.wn5.dotify.databinding.ActivitySongListBinding


private const val KEY = "song"

/*
* We are making a navigation to allow our user to transfer from the song selection page
* to a specific song with pause/next/previous functions.
* To access this method, we need aware of the situation and what song being selected.
*/
fun navigateToPlayerActivity(context: Context, clickedSong: Song) {
    val intent = Intent(context, PlayerActivity::class.java)
    val bundle = Bundle().apply() {
        putParcelable(KEY, clickedSong)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}

/*
* We are going to tell the client how many times a song being played
* Also, we are setting this page to become a child of another parent page---songlistactivity.
* In this case, we enable a go back arrow button.
* We are also changing the music cover, artist, album relying on the previous selected song.
*/
class PlayerActivity : AppCompatActivity() {
    private var currentValue = Random.nextInt(0, 10000)
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater).apply { setContentView(root) }
        binding.playTimes.text = currentValue.toString() +  " plays"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        with(binding) {
            val clickedSong = intent.getParcelableExtra<Song>(KEY)
            if (clickedSong != null) {
                binding.musicCover.setImageResource(clickedSong.largeImageID)
                binding.artistName.text = clickedSong.artist
                binding.albumName.text = clickedSong.artist
            }
        }
    }

    /*
    * Given a message of skipping to the previous song
    */
    fun prevSong(view: View) {
        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    /*
    * Given a message of skipping to the next song
    */
    fun nextSong(view: View) {
        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }

    /*
    * Increment the number of play count and display it to the user.
    */
    fun playSong(view: View) {
        val playtimes = findViewById<TextView>(R.id.playTimes)
        currentValue += 1
        playtimes.text = currentValue.toString() +  " plays"
    }


}