import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("app-android-convention")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
    //alias(libs.plugins.vkid)
}

android {
    namespace = "com.redpine.dogshelter"

    defaultConfig {
        applicationId = "com.redpine.dogshelter"
        versionCode = 7
        versionName = "1.2"
        //TODO: test instead with placeholder plugin & init in App
        val localProperties = gradleLocalProperties(rootDir, providers)
        addManifestPlaceholders(
            mapOf(
                "VKIDRedirectHost" to "vk.com",
                "VKIDRedirectScheme" to localProperties.getProperty("redirect"),
                "VKIDClientID" to localProperties.getProperty("client_id"),
                "VKIDClientSecret" to localProperties.getProperty("client_sec")
            )
        )
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
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
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
    coreLibraryDesugaring(libs.desugar)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}
