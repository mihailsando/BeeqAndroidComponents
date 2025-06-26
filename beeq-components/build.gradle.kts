plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
}

group = "com.endava"
version = "1.0.0"

android {
    namespace = "com.endava.beeq_components"
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
        create("EQ") {
            dimension = "branding"
        }
        create("beeqDefault") {
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

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("endavaRelease") {
                from(components["endavaRelease"])
                groupId = "com.endava"
                artifactId = "beeq-components-endava"
                version = "1.0.0"
            }
            create<MavenPublication>("EQRelease") {
                from(components["EQRelease"])
                groupId = "com.endava"
                artifactId = "beeq-components-EQ"
                version = "1.0.0"
            }
            create<MavenPublication>("beeqDefaultRelease") {
                from(components["beeqDefaultRelease"])
                groupId = "com.endava"
                artifactId = "beeq-components-default"
                version = "1.0.0"
            }
        }
    }

    repositories {
        mavenLocal()
    }
}

dependencies {

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

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}