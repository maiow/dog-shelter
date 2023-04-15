package com.redpine.core.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImage(urls: String) {
    Glide.with(this)
        .load(urls)
        .error(android.R.drawable.ic_delete)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(android.R.drawable.ic_menu_camera)
        .centerCrop()
        .into(this)
}