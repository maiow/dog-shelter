package com.redpine.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object Data {
    /**
     * Mix of images with varying aspect ratios
     */
    val images = listOf(

/*
        Image(
            "https://static.tildacdn.com/tild3561-3263-4966-a437-363730383166/1409-058.jpg"),
//            400,
//            200
//        ),
        Image(
            "https://static.tildacdn.com/tild3530-3030-4161-b331-366331636135/1409-101.jpg"),
//            300,
//            200
//        ),
        Image(
            "https://static.tildacdn.com/tild6638-3236-4539-b965-333130613665/1409-102.jpg"),
//            200,
//            150
//        ),
        Image("https://static.tildacdn.com/tild3434-3162-4230-a463-633465336332/1409-103.jpg"),//, 200, 150),
*/

        Image("https://static.tildacdn.com/stor6337-3131-4331-a261-343637396434/37811585.jpg"),
        Image("https://static.tildacdn.com/stor3562-6136-4463-a461-363637333530/11258827.jpg"),
        Image("https://static.tildacdn.com/stor3830-6135-4230-a537-306566626163/77235198.jpg")
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