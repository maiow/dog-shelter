import org.gradle.api.JavaVersion

object Config {

    const val namespace = "com.redpine.dogshelter"
    const val compileSdk = 33

    const val applicationId = "com.redpine.dogshelter"
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"


}