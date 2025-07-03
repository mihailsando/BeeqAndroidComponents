pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "BeeqAndroidComponents"
include(":app", ":beeq-components")
include(":beeq-components")
