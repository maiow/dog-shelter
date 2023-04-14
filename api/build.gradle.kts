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
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {

    implementation (Dependence.Core.core)
    implementation (Dependence.Core.appcompat)
}