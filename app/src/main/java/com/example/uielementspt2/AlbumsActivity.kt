package com.example.uielementspt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView

var songsDisplay = ArrayList<String>()
var albumURI = String

class AlbumsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        val SongsGridView = findViewById<GridView>(R.id.SongsGridView)

        SongsGridView.adapter = AlbumAdapter(applicationContext)
        SongsGridView.onItemClickListener = AdapterView.OnItemClickListener{ parent, v, position, id ->
            val intent = Intent(this, AlbumDetailsActivity::class.java)
            var songsDisplay = arrayListOf<String>()
            var uri: String
            if (position == 0) {
                uri = "@drawable/oned"
                songsDisplay.clear()
                songsDisplay.addAll(resources.getStringArray(R.array.oneDirection))
            } else if (position == 1) {
                uri = "@drawable/fivesec"
                songsDisplay.clear()
                songsDisplay.addAll(resources.getStringArray(R.array.FiveSeconds))
            } else {
                uri = "@drawable/jasond"
                songsDisplay.clear()
                songsDisplay.addAll(resources.getStringArray(R.array.JasonD))
            }
            intent.putStringArrayListExtra("songs", songsDisplay )
            intent.putExtra("album",  uri)
            intent.putExtra("position", position)
            startActivity(intent)
        }


    }
}