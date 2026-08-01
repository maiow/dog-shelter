import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    compileSdkVersion(36)

    defaultConfig {
        minSdk = 24
        targetSdk = 36
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}