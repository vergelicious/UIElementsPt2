package com.example.uielementspt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementspt2.models.Songs
import com.example.uielementspt2.models.SongDatabaseHandler

class EditSongsActivity : AppCompatActivity() {
    lateinit var title : EditText
    lateinit var artist : EditText
    lateinit var album : EditText
    lateinit var updateBtn: Button
    lateinit var songs: Songs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_songs)
        val song_id = intent.getIntExtra("song_id", 0)
        val databaseHandler = SongDatabaseHandler (this)
        songs = databaseHandler.readOne(song_id)
        title = findViewById(R.id.editTitleEditText)
        artist = findViewById( R.id.editArtistEditText)
        album = findViewById(R.id.editAlbumEditText)
        updateBtn = findViewById(R.id.updateBtn)
        title.setText(songs.title)
        album.setText(songs.album)
        artist.setText(songs.artist)

        updateBtn.setOnClickListener {
            val title_string = title.text.toString()
            val artist_string = artist.text.toString()
            val album_string = album.text.toString()
            val updateSong = Songs(
                id = songs.id,
                title = title_string,
                artist = artist_string,
                album = album_string
            )
            if (databaseHandler.update(updateSong)) {
                Toast.makeText(this, "Song Updated!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
            adapter.notifyDataSetChanged()
        }
    }
}