plugins {
    id("lib-android-convention")
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.redpine.profile"

    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(project(":core"))

    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.fragmentKtx)
    implementation(libs.material)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(libs.googleServices)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAuth)
}