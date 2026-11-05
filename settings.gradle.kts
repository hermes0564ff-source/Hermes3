pluginManagement {


        repositories {
            maven("https://en-mirror.ir")
            google()
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
        maven("https://en-mirror.ir")
        google()
        mavenCentral()
    }
}

rootProject.name = "Hermes3"
include(":app")
