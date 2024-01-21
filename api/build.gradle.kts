plugins {
    id("lib-android-convention")
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.redpine.api"
}

dependencies {

    implementation (libs.core)
    implementation (libs.appcompat)
}