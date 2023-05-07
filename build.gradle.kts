// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.21" apply false

    id("com.google.gms.google-services") version "4.3.15" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false

    id("com.github.ben-manes.versions") version "0.46.0" apply false

// Crashlytics Gradle plugin
//id("com.google.firebase.crashlytics") version "2.9.2" apply false
}

apply(plugin = "com.github.ben-manes.versions")

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

tasks.register<Delete>("clean"){
    delete(rootProject.buildDir)
}