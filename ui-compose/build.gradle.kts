plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}

group = "com.endava"
version = "1.0.0"

android {
    namespace = "endava.beeq.compose"
    namespace = "endava.beeq.compose"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
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

afterEvaluate {
    publishing {
        publications {
            components.findByName("endavaRelease")?.let {
                create<MavenPublication>("endavaRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "beeq-compose-endava"
                    version = "1.0.0"
                }
            }
            components.findByName("eqRelease")?.let {
                create<MavenPublication>("eqRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "beeq-compose-eq"
                    version = "1.0.0"
                }
            }

            components.findByName("defaultBrandRelease")?.let {
                create<MavenPublication>("defaultRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "beeq-compose-default"
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

    implementation(project(":design-tokens"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.material.icons.extended )
    implementation(libs.androidx.material3)
    implementation(libs.coil.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    implementation (libs.androidx.lifecycle.viewmodel.compose)


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}