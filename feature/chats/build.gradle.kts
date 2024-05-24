import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("lib-android-convention")
    alias(libs.plugins.kapt)
    alias(libs.plugins.safeArgs)
}

android {
    namespace = "com.redpine.chats"

    defaultConfig {
        val localProperties = gradleLocalProperties(rootDir)
        buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("API_KEY")}\"")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
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

    implementation(libs.streamChat)
    implementation(libs.streamChatOffline)
    implementation(libs.streamChatUi)

    implementation(libs.lifecycle.runtime)
    implementation(libs.coil)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}
