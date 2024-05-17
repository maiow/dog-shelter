plugins {
    id("lib-android-convention")
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.redpine.chats"

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

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}