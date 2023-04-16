package com.redpine.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object Data {
    /**
     * Mix of images with varying aspect ratios
     */
    val images = listOf(
//        Image("https://static.tildacdn.com/stor6337-3131-4331-a261-343637396434/37811585.jpg"),
//        Image("https://static.tildacdn.com/stor3562-6136-4463-a461-363637333530/11258827.jpg"),
//        Image("https://static.tildacdn.com/stor3830-6135-4230-a537-306566626163/77235198.jpg")
        Image("https://firebasestorage.googleapis.com/v0/b/dog-shelter-d6e3e.appspot.com/o/news%2F33b2KNStxsk.jpg?alt=media&token=8e8979b0-0ce1-4dcf-adc1-b4c61a76ce02"),
        Image("https://firebasestorage.googleapis.com/v0/b/dog-shelter-d6e3e.appspot.com/o/news%2FvRgs4P4iyEs.jpg?alt=media&token=f3d1ddf2-c4a3-4102-a31b-cea6e567ba15"),
        Image("https://firebasestorage.googleapis.com/v0/b/dog-shelter-d6e3e.appspot.com/o/news%2F33b2KNStxsk.jpg?alt=media&token=8e8979b0-0ce1-4dcf-adc1-b4c61a76ce02"),

        )
}

@Parcelize
data class Image(
    val url: String,
//    val width: Int,
//    val height: Int
) : Parcelable {
    //val aspectRatio: Float get() = width.toFloat() / height.toFloat()
}