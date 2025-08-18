plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.endava.design_tokens"
    compileSdk = 35

    defaultConfig {
        minSdk = 21

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

val tokensVersion = project.findProperty("DESIGN_TOKENS_VERSION")?.toString() ?: "1.0.0"
val baseName = project.findProperty("DESIGN_TOKENS_NAME")?.toString() ?: "design-tokens"

val tokensFlavors = listOf(
    "endava" to "endavaRelease",
    "eq" to "eqRelease",
    "default" to "defaultBrandRelease"
)

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

tasks.register("renameTokensAars") {
    group = "publishing"
    description = "Rename design-tokens AARs to include version"

    dependsOn("assembleRelease")

    doLast {
        val outputDir = buildDir.resolve("outputs/aar")
        tokensFlavors.forEach { (flavor, _) ->
            val original = outputDir.resolve("$baseName-$flavor-release.aar")
            val renamed = outputDir.resolve("$baseName-$flavor-v$tokensVersion.aar")
            if (original.exists()) {
                original.renameTo(renamed)
                println("Renamed: ${renamed.name}")
            } else {
                println("Not found: ${original.name}")
            }
        }
    }
}

afterEvaluate {
    tasks.register("publishTokensLocally") {
        group = "publishing"
        description = "Build, rename, publish design-tokens AARs and open folder"

        dependsOn("renameTokensAars")

        tokensFlavors.forEach { (_, componentName) ->
            val taskName = "publish${componentName.replaceFirstChar { it.uppercaseChar() }}PublicationToMavenLocal"
            tasks.findByName(taskName)?.let {
                dependsOn(it)
            }
        }

        doLast {
            val aarFolder = buildDir.resolve("outputs/aar")
            println("Opening AAR folder: ${aarFolder.absolutePath}")
            openFolderCrossPlatform(aarFolder)
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            components.findByName("endavaRelease")?.let {
                create<MavenPublication>("endavaRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "design-tokens-endava"
                    version = "1.0.0"
                }
            }
            components.findByName("eqRelease")?.let {
                create<MavenPublication>("eqRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "design-tokens-eq"
                    version = "1.0.0"
                }
            }
            components.findByName("defaultBrandRelease")?.let {
                create<MavenPublication>("defaultRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "design-tokens-default"
                    version = "1.0.0"
                }
            }
        }

        repositories {
            mavenLocal()
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}