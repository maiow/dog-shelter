plugins {
    id("lib-android-convention")
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