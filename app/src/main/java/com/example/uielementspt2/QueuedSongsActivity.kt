package com.example.uielementspt2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class QueuedSongsActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val desc = "Notification"

    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, qSongs)
        val queuedSongsListView = findViewById<ListView>(R.id.qSongsListView)
        queuedSongsListView.adapter = adapter
        registerForContextMenu(queuedSongsListView)
    }

        override fun onCreateContextMenu(
                menu: ContextMenu?,
                v: View?,
                menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            super.onCreateContextMenu(menu, v, menuInfo)
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.remove_menu, menu)
        }

        override fun onContextItemSelected(item: MenuItem): Boolean {
            val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            return when (item.itemId) {
                R.id.removeSong -> {
                    val song = qSongs[menuInfo.position]
                    qSongs.removeAt(menuInfo.position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "$song is removed from the Queue.", Toast.LENGTH_SHORT).show()

                    if (qSongs.size <= 0) {
                        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notificationChannel = NotificationChannel(
                                    channelId,desc,NotificationManager.IMPORTANCE_HIGH)
                            notificationChannel.enableLights(true)
                            notificationChannel.lightColor = Color.GREEN
                            notificationChannel.enableVibration(false)
                            notificationManager.createNotificationChannel(notificationChannel)

                            builder = Notification.Builder(this,channelId)
                                    .setContentTitle("Song Queue")
                                    .setSmallIcon(R.drawable.ic_launcher_background)
                                    .setContentIntent(pendingIntent)
                                    .setContentText("Your song queue is empty!")
                        } else {
                            builder = Notification.Builder(this)
                                    .setContentTitle("Song Queue")
                                    .setSmallIcon(R.drawable.ic_launcher_background)
                                    .setContentIntent(pendingIntent)
                                    .setContentText("Your song queue is empty!")
                        }
                        notificationManager.notify(1234, builder.build())
                    }
                    true
                }
                else -> {
                    return super.onContextItemSelected(item)
                }
            }
        }
    }