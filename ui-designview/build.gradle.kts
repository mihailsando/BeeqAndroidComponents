plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.endava.ui_designview"
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
        create("endava")  { dimension = "branding" }
        create("eq")      { dimension = "branding" }
        create("default") { dimension = "branding" }
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
    beforeVariants { variant ->
        if (variant.buildType == "debug") {
            variant.enable = false
        }
    }
}

// Read version and base name from gradle.properties
val moduleVersion = (findProperty("UI_DESIGNVIEW_VERSION") as String?) ?: "1.0.0"
val outputBase   = (findProperty("BINDVIEW_NAME") as String?) ?: "ui-designview"

// Optional: publish per-flavor
afterEvaluate {
    publishing {
        publications {
            components.findByName("endavaRelease")?.let {
                create<MavenPublication>("uiDesignviewEndavaRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "${outputBase}-endava"
                    version = moduleVersion
                }
            }
            components.findByName("eqRelease")?.let {
                create<MavenPublication>("uiDesignviewEqRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "${outputBase}-eq"
                    version = moduleVersion
                }
            }
            components.findByName("defaultRelease")?.let {
                create<MavenPublication>("uiDesignviewDefaultRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "${outputBase}-default"
                    version = moduleVersion
                }
            }
        }
        repositories { mavenLocal() }
    }
}

tasks.register("renameDesignviewAars") {
    group = "publishing"
    description = "Rename ui-designview AARs to include version"
    dependsOn("assembleEndavaRelease", "assembleEqRelease", "assembleDefaultRelease")

    doLast {
        val out = buildDir.resolve("outputs/aar")
        listOf("endava", "eq", "default").forEach { flavor ->
            val orig = out.resolve("$outputBase-$flavor-release.aar")
            val dest = out.resolve("$outputBase-$flavor-$moduleVersion.aar")
            if (orig.exists()) {
                orig.renameTo(dest)
                println("Renamed: ${dest.name}")
            } else {
                println("Not found: ${orig.name}")
            }
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
    implementation(libs.coil)
}
