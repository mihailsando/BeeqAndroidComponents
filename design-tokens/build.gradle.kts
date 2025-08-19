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

// Read version and base name from gradle.properties
val tokensVersion = (findProperty("DESIGN_TOKENS_VERSION") as String?) ?: "1.0.0"
val baseName     = (findProperty("DESIGN_TOKENS_NAME") as String?) ?: "design-tokens"

afterEvaluate {
    publishing {
        publications {
            components.findByName("endavaRelease")?.let {
                create<MavenPublication>("endavaRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "$baseName-endava"
                    version = tokensVersion
                }
            }
            components.findByName("eqRelease")?.let {
                create<MavenPublication>("eqRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "$baseName-eq"
                    version = tokensVersion
                }
            }
            // Make sure the component name matches your actual variant name.
            components.findByName("defaultRelease")?.let {
                create<MavenPublication>("defaultRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "$baseName-default"
                    version = tokensVersion
                }
            }
        }
        repositories { mavenLocal() }
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