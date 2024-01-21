plugins {
    id("com.android.library")
    id("base-android-convention")
    id("kotlin-android")
}

android {
    kotlinOptions {
        jvmTarget = "17"
    }
}