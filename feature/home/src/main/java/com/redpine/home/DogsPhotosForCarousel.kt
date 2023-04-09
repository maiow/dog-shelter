package com.redpine.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object Data {
    /**
     * Mix of images with varying aspect ratios
     */
    val images = listOf(
        Image(com.redpine.core.R.drawable.dog2, 250, 250),
        Image(R.drawable.start_png_2, 250, 250),
        Image(R.drawable.start_png_3, 250, 250),
        Image(com.redpine.core.R.drawable.dog2, 250, 250),
        Image(R.drawable.start_png_1, 250, 250),
    )
}

@Parcelize
data class Image(
    val url: Int,
    val width: Int,
    val height: Int
) : Parcelable {
    val aspectRatio: Float get() = width.toFloat() / height.toFloat()
}