package com.example.uielementspt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.uielementspt2.models.SongDatabaseHandler
import com.example.uielementspt2.models.Songs
import com.google.android.material.snackbar.Snackbar

val qSongs = ArrayList<String>()

lateinit var songsArray: MutableList<Songs>
lateinit var adapter: ArrayAdapter<Songs>

class FirstActivity : AppCompatActivity() {
    lateinit var songsDatabaseHandler: SongDatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        songsDatabaseHandler = SongDatabaseHandler(this)
        songsArray = songsDatabaseHandler.read()

        adapter = ArrayAdapter<Songs>(this, android.R.layout.simple_list_item_1, songsArray)
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
                    val songs = songsArray[menuInfo.position].title
                    qSongs.add(songs)
                    val snackbar = Snackbar.make(findViewById(R.id.songsListView), "${songsArray[menuInfo.position]} is added to the Queue.", Snackbar.LENGTH_LONG)
                    snackbar.setAction("Song Queue", View.OnClickListener {
                        val intent = Intent(this, QueuedSongsActivity::class.java)
                        startActivity(intent)
                    })
                    snackbar.show()
                    true
                }
                R.id.editSong -> {
                    val intent = Intent (this, EditSongsActivity::class.java)
                    val song_id = songsArray[menuInfo.position].id
                    intent.putExtra("song_id", song_id)
                    startActivity(intent)
                    true
                }
                R.id.deleteSong -> {
                    val song = songsArray[menuInfo.position]
                    if(songsDatabaseHandler.delete(song)){
                        songsArray.removeAt(menuInfo.position)
                        adapter.notifyDataSetChanged()
                        Toast.makeText(this, "Song deleted.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show()
                    }
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
            R.id.add_songs -> {
                val intent = Intent(this, AddSongsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}