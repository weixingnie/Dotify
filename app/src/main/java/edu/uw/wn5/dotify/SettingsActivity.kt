package edu.uw.wn5.dotify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.ericchee.songdataprovider.Song
import edu.uw.wn5.dotify.databinding.ActivitySettingsBinding
import kotlin.random.Random

/*
* We are making setting page as home for all the fragments.
*/
private const val KEY = "songName"
private const val PLAY_COUNT = "playCount"


fun navigateToSettingsActivity(context: Context, clickedSong: Song, songClickedTimes: Int) {
    val intent = Intent(context, SettingsActivity::class.java)

    val bundle = Bundle().apply() {
        putParcelable(KEY, clickedSong)
        putInt(PLAY_COUNT, songClickedTimes)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}

/*
* We passing down our bundle parameters for statics view.
* also, we are creating the action bar so people could go back to
* this home/setting fragment anytime when they pressed the back arrow.
*/
class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private val navController by lazy { findNavController(R.id.home) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        val values: Bundle? = intent.extras
        if (values != null) {
            navController.setGraph(R.navigation.nav_graph, values)
        }
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}