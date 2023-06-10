plugins {
    id(Plugins.application)
    id(Plugins.android)
    id(Plugins.kapt)
    id(Plugins.googleServices)
}

android {
    namespace = Config.namespace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
//        debug {
//            isMinifyEnabled = false
//            applicationIdSuffix = ".beta"
//        }
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":api"))
    implementation(project(":feature:home"))
    implementation(project(":feature:favorites"))
    //implementation(project(":feature:chats"))
    implementation(project(":core"))

    implementation(Dependence.Core.core)
    implementation(Dependence.Core.appcompat)
    implementation(Dependence.Core.material)
    implementation(Dependence.Core.constraint)
    implementation(Dependence.Core.fragmentKtx)
    implementation(Dependence.Core.navigationFragment)
    implementation(Dependence.Core.navigationUi)
    implementation(Dependence.Core.splashScreen)

    //debugImplementation(Dependence.Core.leakCanary)

    implementation(Dependence.Firebase.googleServices)
    implementation(platform(Dependence.Firebase.firebaseBom))
    implementation(Dependence.Firebase.firebaseAnalytics)
    implementation(Dependence.Firebase.firebaseAuth)
    //implementation(Dependence.Firebase.firebaseCore)
    implementation(Dependence.Firebase.firebaseDatabase)

    implementation(Dependence.Di.dagger)
    kapt(Dependence.Di.daggerCompiler)

}