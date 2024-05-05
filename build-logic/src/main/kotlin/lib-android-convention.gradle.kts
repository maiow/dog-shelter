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

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}
