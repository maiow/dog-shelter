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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dependencies {

     implementation (Dependence.Core.core)
     implementation (Dependence.Core.appcompat)
    }
}