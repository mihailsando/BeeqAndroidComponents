import java.util.Locale

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
}

// read from gradle.properties
val releaseVersion: String = (findProperty("RELEASE_VERSION") as String?) ?: "1.0.0"
val tokensVersion: String = (findProperty("DESIGN_TOKENS_VERSION") as String?) ?: releaseVersion
val designviewVersion: String = (findProperty("UI_DESIGNVIEW_VERSION") as String?) ?: releaseVersion

// where to export -> folder is RELEASE_VERSION
val exportDir = layout.buildDirectory.dir("exports/$releaseVersion")

fun String.cap() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

fun openFolder(folder: File) {
    if (!folder.exists()) return
    val os = System.getProperty("os.name").lowercase(Locale.ROOT)
    try {
        when {
            os.contains("win") -> ProcessBuilder("explorer", folder.absolutePath).start()
            os.contains("mac") -> ProcessBuilder("open", folder.absolutePath).start()
            os.contains("nux") || os.contains("nix") -> ProcessBuilder("xdg-open", folder.absolutePath).start()
        }
    } catch (_: Exception) {}
}

tasks.register("freshBundleAars") {
    group = "publishing"
    description = "Assemble eqRelease for :design-tokens and :ui-designview, export versioned AARs into exports/$releaseVersion"

    dependsOn(":design-tokens:assembleEqRelease")
    dependsOn(":ui-designview:assembleEqRelease")

    doLast {
        val outDir = exportDir.get().asFile
        outDir.mkdirs()

        // design-tokens
        run {
            val aarDir = project(":design-tokens").layout.buildDirectory.dir("outputs/aar").get().asFile
            val candidate = aarDir.listFiles()?.firstOrNull {
                it.extension == "aar" && it.name.contains("-eq-release")
            }
            if (candidate != null) {
                val target = outDir.resolve("design-tokens-eq-$tokensVersion.aar")
                candidate.copyTo(target, overwrite = true)
                println("Collected: ${target.name}")
            } else {
                println("Missing AAR for design-tokens eqRelease in ${aarDir.absolutePath}")
            }
        }

        // ui-designview
        run {
            val aarDir = project(":ui-designview").layout.buildDirectory.dir("outputs/aar").get().asFile
            val candidate = aarDir.listFiles()?.firstOrNull {
                it.extension == "aar" && it.name.contains("-eq-release")
            }
            if (candidate != null) {
                val target = outDir.resolve("ui-designview-$designviewVersion.aar")
                candidate.copyTo(target, overwrite = true)
                println("Collected: ${target.name}")
            } else {
                println("Missing AAR for ui-designview eqRelease in ${aarDir.absolutePath}")
            }
        }

        println("AARs exported to: ${outDir.absolutePath}")
        openFolder(outDir)
    }

    outputs.upToDateWhen { false }
}