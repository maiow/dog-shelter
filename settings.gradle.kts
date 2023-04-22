pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "DogShelter"
include(":app")
include(":feature:favorites")
include(":feature:profile")
include(":feature:home")
include(":feature:chats")
include(":core")
include(":api")