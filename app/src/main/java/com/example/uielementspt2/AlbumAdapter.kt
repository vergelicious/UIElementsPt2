package com.example.uielementspt2

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*

class AlbumAdapter(val c: Context):BaseAdapter() {
    private val mContext: Context
    init {
        mContext = c
    }
    var thumbImages = arrayOf(
        R.drawable.oned,
        R.drawable.fivesec,
        R.drawable.jasond
    )
    override fun getCount(): Int {
        return thumbImages.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(mContext)
        imageView.setLayoutParams(GridView@AbsListView.LayoutParams(520, 520))
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setPadding(5, 13, 5, 13)
        imageView.setImageResource(thumbImages[position])
        return imageView
    }
}

