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
include(":app", ":ui-compose")
include(":beeq-components")
include(":ui-designview")
include(":design-tokens")
