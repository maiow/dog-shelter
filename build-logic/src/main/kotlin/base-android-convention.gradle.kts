import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    compileSdkVersion(34)

    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}