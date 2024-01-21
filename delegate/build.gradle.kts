plugins {
    id("lib-android-convention")
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.redpine.delegate"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.paging)
    implementation(libs.core)
    implementation(libs.appcompat)
}