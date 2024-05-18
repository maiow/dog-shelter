plugins {
    id("lib-android-convention")
}

android {
    namespace = "com.redpine.api"
}

dependencies {

    implementation (libs.core)
    implementation (libs.appcompat)
}