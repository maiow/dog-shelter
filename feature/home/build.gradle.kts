
plugins {
    id(Plugins.library)
    id(Plugins.android)
    id(Plugins.kapt)
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

    dependencies {

        implementation(project(":api"))
        implementation(project(":core"))

        implementation(Dependence.Core.core)
        implementation(Dependence.Core.appcompat)
        implementation(Dependence.Core.fragmentKtx)
        implementation(Dependence.Core.material)

        implementation(Dependence.Di.dagger)
        kapt(Dependence.Di.kapt)
    }

}
dependencies {
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
}
