package com.example.uielementspt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class QueuedSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        val songsDisplayList = intent.getStringArrayListExtra("songs")
        val songsDisplayArray = songsDisplayList!!.toTypedArray()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsDisplayArray)
        val songsListView = findViewById<ListView>(R.id.qSongsListView)
        songsListView.adapter = adapter

    }
}