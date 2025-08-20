pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")//NON-NLS
                includeGroupByRegex("com\\.google.*")//NON-NLS
                includeGroupByRegex("androidx.*")//NON-NLS
            }
        }
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

rootProject.name = "Shopping"//NON-NLS
include(":app")//NON-NLS
 