import java.net.URI

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven(url = "https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-android/")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            mavenContent {
                includeGroupByRegex(".*google.*")
                includeGroupByRegex(".*android.*")
            }
        }
        mavenCentral()
        maven {
            url = URI("https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-android/")
        }
    }
}
rootProject.name = "DogShelter"

includeBuild("build-logic")

include(":app")
include(":feature:favorites")
include(":feature:profile")
include(":feature:home")
include(":feature:auth")
//include(":feature:chats")
include(":core")
include(":delegate")
