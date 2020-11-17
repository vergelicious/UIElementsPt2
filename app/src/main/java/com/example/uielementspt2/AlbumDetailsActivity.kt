package com.example.uielementspt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        val uri = intent.getStringExtra("album")
        val songsDisplay = intent.getStringArrayListExtra("songs")

        var songsArray = songsDisplay!!.toTypedArray()

        val AlbumCover = findViewById<ImageView>(R.id.albumCover)
        var imageResource = getResources().getIdentifier(uri, null, getPackageName())
        var resource = getResources().getDrawable(imageResource)
        AlbumCover.setImageDrawable(resource)

        val albumDetailsListView = findViewById<ListView>(R.id.albumNameTextView)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        albumDetailsListView.adapter = adapter

        Log.i("song", songsDisplay.toString())
    }
}