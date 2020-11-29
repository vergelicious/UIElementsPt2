package com.example.uielementspt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementspt2.models.SongDatabaseHandler
import com.example.uielementspt2.models.Songs

class AddSongsActivity : AppCompatActivity() {
    lateinit var title: EditText
    lateinit var author: EditText
    lateinit var album: EditText
    lateinit var addBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_songs)

        title = findViewById(R.id.titleEditText)
        author = findViewById(R.id.authorEditText)
        album = findViewById(R.id.albumEditText)
        addBtn = findViewById(R.id.addSongBtn)

        val databaseHandler = SongDatabaseHandler(this)

        addBtn.setOnClickListener {
            val title_string = title.text.toString()
            val artist_string = author.text.toString()
            val album_string = album.text.toString()
            val songs = Songs(title = title_string, artist = artist_string, album =  album_string)

            if (databaseHandler.create(songs)){
                Toast.makeText(this, "Song Added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
            adapter.notifyDataSetChanged()
            clear()
        }
    }
    fun clear() {
        title.text.clear()
        author.text.clear()
        album.text.clear()
    }
}