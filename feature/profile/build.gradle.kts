plugins {
    id("lib-android-convention")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.redpine.profile"

    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(project(":api"))
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
    ksp(libs.daggerCompiler)

    implementation(libs.googleServices)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAuth)
}