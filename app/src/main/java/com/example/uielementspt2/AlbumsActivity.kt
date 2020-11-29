package com.example.uielementspt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import com.example.uielementspt2.models.Album
import com.example.uielementspt2.models.SongDatabaseHandler

var albumSongs = ArrayList<String>()
var albumURI = String
lateinit var albumAdapter: ArrayAdapter<Album>
class AlbumsActivity : AppCompatActivity() {
    lateinit var title: String
    lateinit var listView : ListView
    lateinit var databaseHandler: SongDatabaseHandler
    lateinit var albums: MutableList<Album>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        listView = findViewById(R.id.listView)
        databaseHandler = SongDatabaseHandler(this)
        albums = databaseHandler.readAlbum()
        albumAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albums)
        listView.adapter = albumAdapter
        registerForContextMenu(listView)

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, NewAlbumDetailsActivity::class.java)
            val album_id = albums[position].id
            intent.putExtra("album_id", album_id)
            startActivity(intent)
        }
//        val SongsGridView = findViewById<GridView>(R.id.SongsGridView)
//        SongsGridView.adapter = AlbumAdapter(applicationContext)
//        SongsGridView.onItemClickListener = AdapterView.OnItemClickListener{ parent, v, position, id ->
//            val intent = Intent(this, AlbumDetailsActivity::class.java)
//            var songsDisplay = arrayListOf<String>()
//            var uri: String
//            if (position == 0) {
//                uri = "@drawable/oned"
//                songsDisplay.clear()
//                songsDisplay.addAll(resources.getStringArray(R.array.oneDirection))
//            } else if (position == 1) {
//                uri = "@drawable/fivesec"
//                songsDisplay.clear()
//                songsDisplay.addAll(resources.getStringArray(R.array.FiveSeconds))
//            } else {
//                uri = "@drawable/jasond"
//                songsDisplay.clear()
//                songsDisplay.addAll(resources.getStringArray(R.array.JasonD))
//            }
//            intent.putStringArrayListExtra("songs", songsDisplay )
//            intent.putExtra("album",  uri)
//            intent.putExtra("position", position)
//            startActivity(intent)
//        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.album_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addAlbum -> {
                val intent = Intent(this, AddAlbumActivity::class.java)
                startActivity(intent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = menuInflater
        inflater.inflate(R.menu.edit_album_menu, menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.editAlbum -> {
                val intent = Intent(this, EditAlbumActivity::class.java)
                val album_id = albums[info.position].id
                intent.putExtra("album_id", album_id)
                startActivity(intent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}