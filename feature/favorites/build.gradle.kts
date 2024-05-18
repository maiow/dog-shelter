plugins {
    id("lib-android-convention")
    alias(libs.plugins.kapt)

}

android {
    namespace = "com.redpine.favorites"

    buildFeatures {
        viewBinding = true
    }

}
dependencies {

    implementation(project(":api"))
    implementation(project(":core"))
    implementation(project(":delegate"))

    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.fragmentKtx)
    implementation(libs.material)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.glide)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(libs.googleServices)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAuth)
    implementation(libs.firebaseDatabase)
}