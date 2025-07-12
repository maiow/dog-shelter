import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("app-android-convention")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.redpine.dogshelter"

    defaultConfig {
        applicationId = "com.redpine.dogshelter"
        versionCode = 6
        versionName = "1.1.2"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget("17")
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(project(":api"))
    implementation(project(":feature:home"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:auth"))
    //implementation(project(":feature:chats"))
    implementation(project(":core"))

    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.fragmentKtx)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.splashScreen)

    //debugImplementation(libs.leakCanary)

    implementation(libs.googleServices)
    implementation(platform(libs.firebaseBom))
    implementation(libs.bundles.firebaseDeps)

    implementation(libs.dagger)
    ksp(libs.daggerCompiler)
}
