package com.example.uielementspt2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class QueuedSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queuedsongs)

        val songsDisplayList = intent.getStringArrayListExtra("songs")
        val songsDisplayArray = songsDisplayList!!.toTypedArray()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsDisplayArray)
        val songsListView = findViewById<ListView>(R.id.qSongsListView)
        songsListView.adapter = adapter

    }
}