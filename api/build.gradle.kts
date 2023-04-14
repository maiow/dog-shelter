plugins {
    id (Plugins.library)
    id (Plugins.android)
}

android {
    namespace = "com.redpine.api"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation (Dependence.Core.core)
    implementation (Dependence.Core.appcompat)
}