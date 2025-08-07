plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.endava.ui_designview"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "branding"

    productFlavors {
        create("endava") {
            dimension = "branding"
        }
        create("eq") {
            dimension = "branding"
        }
        create("default") {
            dimension = "branding"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

androidComponents {
    beforeVariants {
        it.flavorName?.let {flavor ->
            it.enable = flavor in listOf("endava", "eq", "default")
        }
    }
}

val moduleVersion = project.findProperty("UI_DESIGNVIEW_VERSION")?.toString() ?: "1.0.0"
val outputName = project.findProperty("BINDVIEW_EQ_FILENAME")?.toString() ?: "DS_android_bindviews"
val baseGroup = "com.endava"
val componentName = "eqRelease"

val flavors = listOf(
    "endava" to "endavaRelease",
    "eq" to "eqRelease",
    "default" to "defaultRelease"
)


afterEvaluate {
    publishing {
        publications {
            components.findByName(componentName)?.let {
                create<MavenPublication>("bindviewEqRelease") {
                    from(it)
                    groupId = baseGroup
                    artifactId = outputName
                    version = moduleVersion
                }
            }
        }

        repositories {
            mavenLocal()
        }
    }
}

fun openFolderCrossPlatform(folder: File) {
    if (!folder.exists()) {
        println("Folder does not exist: ${folder.absolutePath}")
        return
    }

    val os = System.getProperty("os.name").lowercase()

    try {
        when {
            os.contains("win") -> ProcessBuilder("explorer", folder.absolutePath).start()
            os.contains("mac") -> ProcessBuilder("open", folder.absolutePath).start()
            os.contains("nux") || os.contains("nix") -> ProcessBuilder("xdg-open", folder.absolutePath).start()
            else -> println("Unsupported OS: $os")
        }
    } catch (e: Exception) {
        println("Failed to open folder: ${e.message}")
    }
}

tasks.register("renameBindviewAar") {
    group = "publishing"
    description = "Rename AAR to custom name: $outputName-eq-$moduleVersion.aar"

    dependsOn("assembleEqRelease")

    doLast {
        val outputDir = buildDir.resolve("outputs/aar")
        val original = outputDir.listFiles()?.firstOrNull { it.name.endsWith("-eq-release.aar") }
        val renamed = outputDir.resolve("$outputName-eq-$moduleVersion.aar")

        if (original != null && original.exists()) {
            original.renameTo(renamed)
            println("Renamed to: ${renamed.name}")
        } else {
            println("Could not find original .aar to rename.")
        }
    }
}

afterEvaluate {
    tasks.register("publishBindviewAarLocally") {
        group = "publishing"
        description = "Build, rename and publish bindview .aar to mavenLocal and open folder"

        dependsOn("renameBindviewAar")
        dependsOn("publishBindviewEqReleasePublicationToMavenLocal")

        doLast {
            val aarFolder = buildDir.resolve("outputs/aar")
            println("Opening AAR folder: ${aarFolder.absolutePath}")
            openFolderCrossPlatform(aarFolder)
        }
    }
}


dependencies {

    implementation(project(":design-tokens"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
