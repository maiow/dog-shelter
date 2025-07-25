import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    compileSdkVersion(35)

    defaultConfig {
        minSdk = 24
        targetSdk = 35
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}