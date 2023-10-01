
plugins {
    id(Plugins.library)
    id(Plugins.android)
    id(Plugins.kapt)
    id(Plugins.safeArgs)
    id(Plugins.parcelize)
}

android {
    namespace = "com.redpine.auth"
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

    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("com.google.firebase:firebase-auth-ktx")

    implementation("com.google.android.gms:play-services-auth:20.6.0")

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
    kapt(Dependence.Di.daggerCompiler)
}
