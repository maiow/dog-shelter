plugins {
    `kotlin-dsl`
}
repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlinGradlePlugin)
    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
}