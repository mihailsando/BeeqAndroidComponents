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
include(":app")
include(":ui-compose")
include(":ui-designview")
include(":design-tokens")
