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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":api"))
    implementation(project(":feature:home"))
    implementation(project(":core"))

    implementation(Dependence.Core.core)
    implementation(Dependence.Core.appcompat)
    implementation(Dependence.Core.material)
    implementation(Dependence.Core.constraint)
    implementation(Dependence.Core.fragmentKtx)

    implementation(Dependence.Firebase.googleServices)
    implementation(platform(Dependence.Firebase.firebaseBom))
    implementation(Dependence.Firebase.firebaseAnalytics)
    implementation(Dependence.Firebase.firebaseAuth)
    //implementation(Dependence.Firebase.firebaseCore)
    implementation(Dependence.Firebase.firebaseDatabase)

    implementation(Dependence.Di.dagger)
    kapt(Dependence.Di.kapt)


}