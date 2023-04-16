
plugins {
    id(Plugins.library)
    id(Plugins.android)
    id(Plugins.kapt)
    id(Plugins.safeArgs)
    id(Plugins.parselize)
}

android {
    namespace = "com.redpine.home"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
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
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
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
    implementation(Dependence.Core.glide)

    implementation(Dependence.Firebase.googleServices)
    implementation(platform(Dependence.Firebase.firebaseBom))
    implementation(Dependence.Firebase.firebaseAuth)
    implementation(Dependence.Firebase.firebaseDatabase)

    implementation(Dependence.Di.dagger)
    kapt(Dependence.Di.kapt)
}
