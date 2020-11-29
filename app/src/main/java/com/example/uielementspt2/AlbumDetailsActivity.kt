package com.example.uielementspt2

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*

class AlbumDetailsActivity : AppCompatActivity() {

    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        val uri = intent.getStringExtra("album")

        val AlbumCover = findViewById<ImageView>(R.id.albumCover)

        val albumDetailsListView = findViewById<ListView>(R.id.albumNameTextView)

        val title = findViewById<TextView>(R.id.albumTitle_new)
        title.setText(intent.getStringExtra("albumTitle").toString())

        var imageResource = getResources().getIdentifier(uri, null, getPackageName())
        var res = getResources().getDrawable(imageResource)
        AlbumCover.setImageDrawable(res)

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, albumSongs)
        albumDetailsListView.adapter = adapter
        registerForContextMenu(albumDetailsListView)

    }
    override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.remove_menu, menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.removeSong -> {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Do you want to remove this song from the Album?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialog, which ->
                            val song = songsArray[menuInfo.position]
                            songsArray.removeAt(menuInfo.position)
                            adapter.notifyDataSetChanged()
                        }).setNegativeButton( "No", DialogInterface.OnClickListener {
                            dialog, which ->
                            dialog.cancel()
                        })
                val alert = dialogBuilder.create()
                alert.setTitle("Remove Song")
                alert.show()
                true
            }
            else -> {
                return super.onContextItemSelected(item)
            }
        }
    }
}