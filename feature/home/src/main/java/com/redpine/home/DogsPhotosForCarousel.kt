package com.redpine.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

object Data {
    /**
     * Mix of images with varying aspect ratios
     */
    val images = listOf(
        Image(com.redpine.core.R.drawable.dog1, 250, 250),
        Image(com.redpine.core.R.drawable.dog1, 250, 250),
        Image(com.redpine.core.R.drawable.dog1, 250, 250),
        Image(com.redpine.core.R.drawable.dog1, 250, 250),
        Image(com.redpine.core.R.drawable.dog1, 250, 250),

//        Image(R.drawable.start_png_1, 250, 250),
//        Image(R.drawable.start_png_2, 250, 250),
//        Image(com.redpine.core.R.drawable.dog1, 250, 250),
//        Image(R.drawable.start_png_3, 250, 250),

//        Image("https://media2.giphy.com/media/ZEBoAmoK5NVNso29xo/200.gif", 356, 200),
//        Image("https://media3.giphy.com/media/W6XOLBwKcMyCeTtO3e/200.gif", 200, 200),
//        Image("https://media0.giphy.com/media/3owypi68EvEuiasmDS/200.gif", 372, 200),
//        Image("https://media4.giphy.com/media/QZybZmviJW0Yn9K1nn/200.gif", 356, 200),
//        Image("https://media3.giphy.com/media/SXqnLsJpKhLU8eLjf1/200.gif", 200, 200),
//        Image("https://media3.giphy.com/media/9kcBLYxWztmmc/200.gif", 356, 200),
//        Image("https://media3.giphy.com/media/26FPBOzkRdjVkUdSo/200.gif", 355, 200),
//        Image("https://media3.giphy.com/media/ehV5y42VAqn8vqKtAB/200.gif", 200, 200),
//        Image("https://media0.giphy.com/media/Kfkk8BPdtQceKQUQxq/200.gif", 200, 200),
//        Image("https://media1.giphy.com/media/Ws3ABiQMrbMv9H7Gnk/200.gif", 150, 200),
//        Image("https://media1.giphy.com/media/1yn0SW0UMOwbprs4pI/200.gif", 356, 200),
//        Image("https://media4.giphy.com/media/xUOxf8izqVvHEBhRO8/200.gif", 200, 200),
//        Image("https://media2.giphy.com/media/3o7aD8HIYCudcacsr6/200.gif", 113, 200),
//        Image("https://media0.giphy.com/media/TKFvmat1Z6mbt17dAw/200.gif", 208, 200),
//        Image("https://media1.giphy.com/media/VI9Q3ZzkOgB0urXE82/200.gif", 200, 200),
//        Image("https://media4.giphy.com/media/3o7TKqiUTQEoewY6eA/200.gif", 291, 200),
//        Image("https://media0.giphy.com/media/l0HlJRPhFnk4qXZKw/200.gif", 356, 200),
//        Image("https://media4.giphy.com/media/QC7k4oOd9lcJEru1cy/200.gif", 356, 200),
//        Image("https://media0.giphy.com/media/l3q2DScd44jwBjpSg/200.gif", 154, 200),
//        Image("https://media2.giphy.com/media/l46C93LNM33JJ1SMw/200.gif", 398, 200),
//        Image("https://media3.giphy.com/media/xUOwG2G6UQ6o6OQgNy/200.gif", 355, 200),
//        Image("https://media1.giphy.com/media/3o84syshVj2Iq8Mf04/200.gif", 356, 200),
//        Image("https://media0.giphy.com/media/DrhMpwvQlIxGw/200.gif", 185, 200),
//        Image("https://media0.giphy.com/media/lqpfJwb30kkVymm1W8/200.gif", 200, 200),
//        Image("https://media2.giphy.com/media/3o84soHWYqD1G8AL5K/200.gif", 356, 200),
//        Image("https://media1.giphy.com/media/l4Jz7HEKl2yGbjzWg/200.gif", 150, 200),
//        Image("https://media3.giphy.com/media/FbxIwpLxkNFXW/200.gif", 138, 200),
//        Image("https://media3.giphy.com/media/iCjhRTjzokCje/200.gif", 200, 200),
//        Image("https://media1.giphy.com/media/YplSkqtJGgj8VAPqir/200.gif", 143, 200),
//        Image("https://media4.giphy.com/media/lcyaEwgNvvoFzpnfDp/200.gif", 152, 200),
//        Image("https://media2.giphy.com/media/EO233xYajs99u/200.gif", 156, 200),
//        Image("https://media1.giphy.com/media/GCvktC0KFy9l6/200.gif", 188, 200),
//        Image("https://media2.giphy.com/media/kiBcwEXegBTACmVOnE/200.gif", 160, 200),
//        Image("https://media0.giphy.com/media/1uLQUtPLbJMQ0/200.gif", 146, 200),
//        Image("https://media4.giphy.com/media/2csuIJj6TmuKA/200.gif", 143, 200),
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