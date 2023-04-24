
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
    implementation(project(":core"))
    implementation(project(":delegate"))

    implementation(Dependence.Core.core)
    implementation(Dependence.Core.appcompat)
    implementation(Dependence.Core.fragmentKtx)
    implementation(Dependence.Core.material)
    implementation(Dependence.Core.navigationFragment)
    implementation(Dependence.Core.navigationUi)
    implementation(Dependence.Core.glide)
    implementation(Dependence.Core.lifecycleLivedata)
    implementation(Dependence.Core.lifecycleViewmodel)

    implementation(Dependence.Firebase.googleServices)
    implementation(platform(Dependence.Firebase.firebaseBom))
    implementation(Dependence.Firebase.firebaseAuth)
    implementation(Dependence.Firebase.firebaseDatabase)

    implementation(Dependence.Di.dagger)
    kapt(Dependence.Di.kapt)
}
