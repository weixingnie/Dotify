package edu.uw.wn5.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var currentValue = Random.nextInt(0, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playtime = findViewById<TextView>(R.id.playTimes)
        playtime.text = currentValue.toString() +  " plays"
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

    /*
    * Help user to choose a new account. User can make a new account or continue with the old one.
     */
    fun switchUser(view: View) {
        val buttonName = findViewById<Button>(R.id.changeUser)
        val currentUser = findViewById<TextView>(R.id.username)
        val inputUser = findViewById<TextView>(R.id.inputUserName)
        if (TextUtils.isEmpty(inputUser.text.toString())) {
            inputUser.error = "The new user can not be empty"
            return;
        }
        if (buttonName.text == "CHANGE USER") {
            buttonName.text = "APPLY"
            currentUser.visibility = View.GONE
            inputUser.visibility = View.VISIBLE
        } else {
            val updateName = inputUser.text
            currentUser.text = updateName
            buttonName.text = "CHANGE USER"
            inputUser.visibility = View.GONE
            currentUser.visibility = View.VISIBLE
        }
    }

}