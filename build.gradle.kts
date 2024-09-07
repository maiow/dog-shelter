plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.googleServices).apply(false)
    alias(libs.plugins.safeArgs).apply(false)
    alias(libs.plugins.crashlytics).apply(false)
    alias(libs.plugins.kapt).apply(false)
    alias(libs.plugins.parcelize).apply(false)
    alias(libs.plugins.vkid).apply(true)
}
//TODO: check if fails because of token expired or this
vkidManifestPlaceholders {
    init(
        clientId = "52257694",
        clientSecret = "VKIDClientSecret"
    )
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
