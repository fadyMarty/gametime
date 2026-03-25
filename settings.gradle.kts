pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "GameTime"
include(":app")

initSubmodules()

include(":uikit")
project(":uikit").projectDir = File("gametime_uikit/uikit")

private fun initSubmodules() {
    val submodulePaths = listOf("gametime_uikit")

    val needsInit = submodulePaths.any { path ->
        File(path).listFiles().isEmpty()
    }

    if (needsInit) {
        ProcessBuilder()
            .command("git", "submodule", "update", "--init", "--recursive")
            .directory(rootDir)
            .redirectErrorStream(true)
            .start()
            .waitFor()
    }
}