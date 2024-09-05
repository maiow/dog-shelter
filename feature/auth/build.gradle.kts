import com.android.SdkConstants.FN_LOCAL_PROPERTIES
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("lib-android-convention")
    alias(libs.plugins.kapt)
    alias(libs.plugins.safeArgs)
}

android {
    namespace = "com.redpine.auth"

//    defaultConfig {
//        val localProperties = gradleLocalProperties(rootDir, providers)
//        buildConfigField("String", "client", "\"${localProperties.getProperty("client")}\"")
//    }

    buildFeatures {
        viewBinding = true
//        buildConfig = true
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

    implementation(libs.googleServices)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAuth)
    implementation(libs.playServices)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}