package com.task.egymtask.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.task.egymtask.R

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context).load(url).error(R.drawable.cross).placeholder(R.drawable.image).centerCrop().into(this)
}