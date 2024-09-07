
plugins {
    id("app-android-convention")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.vkid)
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

    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

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
    implementation(libs.firebaseAnalytics)
    implementation(libs.firebaseAuth)
    implementation(libs.playServices)
    implementation(libs.firebaseDatabase)
    implementation(libs.firebaseCrashlytics)

    implementation(libs.vkOnetap)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}
