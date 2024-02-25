import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("app-android-convention")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.redpine.dogshelter"

    defaultConfig {
        applicationId = "com.redpine.dogshelter"
        versionCode = 5
        versionName = "1.1.1"

        val localProperties = gradleLocalProperties(rootDir)
        buildConfigField("String", "API_KEY", "\"${localProperties.getProperty("API_KEY")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "17"
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
    implementation(project(":feature:chats"))
    implementation(project(":core"))

    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.fragmentKtx)
    implementation(libs.navigationFragment)
    implementation(libs.navigationUi)
    implementation(libs.splashScreen)

    implementation(libs.streamChat)
    implementation(libs.streamChatUi)
    implementation(libs.streamChatOffline)

    implementation(libs.lifecycle.runtime)
    implementation(libs.coil)

    //debugImplementation(libs.leakCanary)

    implementation(libs.googleServices)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAnalytics)
    implementation(libs.firebaseAuth)
    implementation(libs.firebaseDatabase)
    implementation(libs.firebaseCrashlytics)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}
