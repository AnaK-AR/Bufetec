plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.bufetec"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.bufetec"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Calendly API
        val calendlyApiToken = project.findProperty("calendlyApiToken") as String? ?: ""
        val calendlyUserUri = project.findProperty("calendlyUserUri") as String? ?: ""

        if (calendlyApiToken.isEmpty()) {
            throw GradleException("Calendly API Token is not set. Please define 'calendlyApiToken' in local.properties.")
        }
        if (calendlyUserUri.isEmpty()) {
            throw GradleException("Calendly User URI is not set. Please define 'calendlyUserUri' in local.properties.")
        }

        buildConfigField("String", "CALENDLY_API_TOKEN", "\"$calendlyApiToken\"")
        buildConfigField("String", "CALENDLY_USER_URI", "\"$calendlyUserUri\"")


        if (calendlyApiToken.isEmpty()) {
            println("Calendly API Token is empty.")
        } else {
            println("Calendly API Token is set.")
        }

        if (calendlyUserUri.isEmpty()) {
            println("Calendly User URI is empty.")
        } else {
            println("Calendly User URI is set.")
        }
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
        buildConfig = true  // Enable BuildConfig generation
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)

    // Navigation Component for Compose
    implementation(libs.androidx.navigation.compose)

    // Coil for image loading
    implementation(libs.coil.compose)

    // Retrofit and networking
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)

    // ViewModel for Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Material Design Components
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)

    // Accompanist libraries
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    // Recursos - Video
    implementation (libs.core)

    // Moshi for JSON parsing
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)


    // Material Components (for DatePicker)
    implementation(libs.material3)

    // AndroidX libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Testing libraries
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}