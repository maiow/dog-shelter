plugins {
    id(Plugins.library)
    id(Plugins.android)
    id(Plugins.kapt)
    id(Plugins.safeArgs)

}

android {
    namespace = "com.redpine.home"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

}
dependencies {
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.2")

    implementation(project(":api"))
    implementation(project(":core"))

    implementation(Dependence.Core.core)
    implementation(Dependence.Core.appcompat)
    implementation(Dependence.Core.fragmentKtx)
    implementation(Dependence.Core.material)
    implementation(Dependence.Core.navigationFragment)
    implementation(Dependence.Core.navigationUi)

    implementation(Dependence.Di.dagger)

    implementation(Dependence.Firebase.googleServices)
    implementation(platform(Dependence.Firebase.firebaseBom))
    implementation(Dependence.Firebase.firebaseAuth)
    kapt(Dependence.Di.kapt)
}
