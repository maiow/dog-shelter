plugins {
    id("lib-android-convention")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.redpine.core"

    buildFeatures {
        viewBinding = true
    }
}
dependencies {

    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.fragmentKtx)
    implementation(libs.material)
    implementation(libs.navigationFragment)
    implementation(libs.glide)

    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseDatabase)
    implementation(libs.firebaseAuth)

}