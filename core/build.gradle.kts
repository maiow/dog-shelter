plugins {
    id(Plugins.library)
    id(Plugins.android)
}

android {
    namespace = "com.redpine.core"
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

    implementation(Dependence.Core.core)
    implementation(Dependence.Core.appcompat)
    implementation(Dependence.Core.fragmentKtx)
    implementation(Dependence.Core.material)
    implementation(Dependence.Core.glide)
    implementation(Dependence.Core.adapterDelegates)

    implementation("com.google.firebase:firebase-database-ktx:20.2.0")
    //implementation("com.hannesdorfmann:adapterdelegates4-pagination:4.3.2")
    //implementation("androidx.paging:paging-runtime:3.1.1")
}