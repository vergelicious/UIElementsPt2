package com.example.uielementspt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    val qSongs = ArrayList<String>()
    val songsArray = arrayOf("Amnesia", "Best Song Ever", "Easier", "History", "Lie To Me", "Night Changes",
            "No Shame", "Old Me", "Savage Love", "Steal My Girl", "Swalla", "Take You Dancing",
            "Talk Dirty", "Wiggle", "You & I")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val songsListView = findViewById<ListView>(R.id.songsListView)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)
    }

    override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = (menuInflater).apply {
            inflate(R.menu.songs_menu, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.queue_option -> {
                qSongs.add(songsArray[menuInfo.position])
                true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_to_songs -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.go_to_album -> {
                startActivity(Intent(this, AlbumActivity::class.java))
                true
            }
            R.id.go_to_queue -> {
                val intent = Intent(this, SongsActivity::class.java)
                intent.putStringArrayListExtra("songs", qSongs)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}