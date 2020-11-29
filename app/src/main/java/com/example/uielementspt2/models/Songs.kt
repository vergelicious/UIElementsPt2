package com.example.uielementspt2.models

class Songs (var id: Int = 0 , var title: String , var artist : String , var album : String) {
    override fun toString(): String {
        return "TITLE: $title \r ARTIST: $artist \r ALBUM: $album"
    }
}