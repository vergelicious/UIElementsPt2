package com.example.uielementspt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar

val qSongs = ArrayList<String>()
val songsArray = arrayListOf<String>()

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        songsArray.addAll(resources.getStringArray(R.array.oneDirection))
        songsArray.addAll(resources.getStringArray(R.array.FiveSeconds))
        songsArray.addAll(resources.getStringArray(R.array.JasonD))

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        var songsListView = findViewById<ListView>(R.id.songsListView)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)
    }

    override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.songs_menu, menu)
    }

        override fun onContextItemSelected(item: MenuItem): Boolean {
            val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            return when (item.itemId) {
                R.id.queue_option -> {
                    qSongs.add(songsArray[menuInfo.position])
                    val snackbar = Snackbar.make(findViewById(R.id.songsListView), "${songsArray[menuInfo.position]} is added to the Queue.", Snackbar.LENGTH_LONG)
                    snackbar.setAction("Song Queue", View.OnClickListener {
                        val intent = Intent(this, QueuedSongsActivity::class.java)
                        startActivity(intent)
                    })
                    snackbar.show()
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
                startActivity(Intent(this, FirstActivity::class.java))
                true
            }
            R.id.go_to_album -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.go_to_queue -> {
                val intent = Intent(this, QueuedSongsActivity::class.java)
                intent.putStringArrayListExtra("songs", qSongs)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}