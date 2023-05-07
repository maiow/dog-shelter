plugins {
    id(Plugins.library)
    id(Plugins.android)
}

android {
    namespace = "com.redpine.delegate"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
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
    implementation(Dependence.Core.paging)
    implementation(Dependence.Core.core)
    implementation(Dependence.Core.appcompat)
}