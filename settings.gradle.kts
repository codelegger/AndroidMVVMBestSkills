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

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "AndroidMVVMBestSkills"
include(":app")

// Core modules: shared building blocks
include(":core:common")
include(":core:security")
include(":core:network")
include(":core:database")
include(":core:testing")

// Feature modules: user-facing capabilities
include(":feature:auth")
include(":feature:secure-storage")
include(":feature:network-security")
include(":feature:device-integrity")
include(":feature:biometrics")
