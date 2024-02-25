pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
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
        maven { url = uri("https://www.jitpack.io") }
    }
}
rootProject.name = "DogShelter"

includeBuild("build-logic")

include(":app")
include(":feature:favorites")
include(":feature:profile")
include(":feature:home")
include(":feature:auth")
include(":feature:chats")
include(":core")
include(":api")
include(":delegate")
