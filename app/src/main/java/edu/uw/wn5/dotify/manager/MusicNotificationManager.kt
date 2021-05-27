package edu.uw.wn5.dotify.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ericchee.songdataprovider.Song
import edu.uw.wn5.dotify.PlayerActivity
import edu.uw.wn5.dotify.R
import kotlin.random.Random


private const val NEW_MUSIC_CHANNEL = "NEW_MUSIC_CHANNEL"
class MusicNotificationManager (
    private val context: Context
){
    private val notificationManager = NotificationManagerCompat.from(context)



    init {
        initNotificationChannels()
    }

    fun publishNewMusicNotification(song: Song) {
        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context, NEW_MUSIC_CHANNEL)
            .setSmallIcon(R.drawable.ic_play)
            .setContentTitle("${song.artist} just released a new song!!!")
            .setContentText("Listen to ${song.title} now on Dotify")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(notificationManager) {
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    private fun initNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "New Music"
            val descriptionText = "update"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NEW_MUSIC_CHANNEL, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

}