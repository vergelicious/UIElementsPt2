package com.example.uielementspt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielementspt2.models.AlbumSongs
import com.example.uielementspt2.models.SongDatabaseHandler

class AddSongToAlbumActivity : AppCompatActivity() {
    lateinit var albumSongsTitle: EditText
    lateinit var addAlbumSongs: Button
    lateinit var albumSongs: AlbumSongs
    lateinit var albumTitleEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song_to_album)

        albumTitleEditText = findViewById(R.id.titleOftheAlbum)

        var albumTitle = intent.getStringExtra("albumTitle")
        albumSongsTitle = findViewById(R.id.albumSongTitleEditText)
        addAlbumSongs = findViewById(R.id.addSongToAlbumBtn)

        val databaseHandler = SongDatabaseHandler(this)
        albumTitleEditText.setText(albumTitle)

        addAlbumSongs.setOnClickListener{
            val title_string = albumSongsTitle.text.toString()
            val album_title = albumTitleEditText.text.toString()
            val albumSongs = AlbumSongs(albumSongs = title_string, albumTitle = album_title)
            if (databaseHandler.createAlbumSongs(albumSongs)){
                Toast.makeText(this, "Song Added To Album!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
            albumSongsArrayAdapter.notifyDataSetChanged()
            clear()
        }
    }
    fun clear(){
        albumSongsTitle.text.clear()
    }
}