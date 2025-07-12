plugins {
    id("lib-android-convention")
    alias(libs.plugins.ksp)
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
    ksp(libs.daggerCompiler)
}