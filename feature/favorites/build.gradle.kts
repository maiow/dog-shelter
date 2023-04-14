plugins {
    id(Plugins.library)
    id(Plugins.android)
    id(Plugins.kapt)
}

android {
    namespace = "com.redpine.favorites"
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

    buildFeatures {
        viewBinding = true
    }

}
dependencies {

    implementation(project(":api"))
    implementation(project(":core"))

    implementation(Dependence.Core.core)
    implementation(Dependence.Core.appcompat)
    implementation(Dependence.Core.fragmentKtx)
    implementation(Dependence.Core.material)
    implementation(Dependence.Core.navigationFragment)
    implementation(Dependence.Core.navigationUi)

    implementation(Dependence.Di.dagger)
    kapt(Dependence.Di.kapt)
}