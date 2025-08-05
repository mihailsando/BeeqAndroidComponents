plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "endava.beeq.designview"
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

afterEvaluate {
    publishing {
        publications {
            components.findByName("endavaRelease")?.let {
                create<MavenPublication>("endavaRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "beeq-bindview-endava"
                    version = "1.0.0"
                }
            }
            components.findByName("eqRelease")?.let {
                create<MavenPublication>("eqRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "beeq-bindview-eq"
                    version = "1.0.0"
                }
            }

            components.findByName("defaultBrandRelease")?.let {
                create<MavenPublication>("defaultRelease") {
                    from(it)
                    groupId = "com.endava"
                    artifactId = "beeq-bindview-default"
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
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
