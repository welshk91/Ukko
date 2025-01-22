plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.github.welshk.ukko"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.github.welshk.ukko"
        minSdk = 22
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        val STRING = "String"
        val BOOLEAN = "boolean"
        val TRUE = "true"
        val FALSE = "false"

        val API_KEY = "API_KEY"
        val OPENWEEATHERMAP_API_KEY = "\"41ff78382fe9fc7356ff694b1ec53714\""

        val USE_MOCK_DATA = "USE_MOCK_DATA"
        val MOCK_CURRENT_WEATHER = "MOCK_CURRENT_WEATHER"
        val MOCK_CURRENT_WEATHER_FILE = "\"tampa_thunderstorm.json\""

        getByName("release") {
            buildConfigField(STRING, API_KEY, OPENWEEATHERMAP_API_KEY)
            buildConfigField(BOOLEAN, USE_MOCK_DATA, FALSE)
            buildConfigField(STRING, MOCK_CURRENT_WEATHER, MOCK_CURRENT_WEATHER_FILE)
            isMinifyEnabled = false
        }

        getByName("debug") {
            buildConfigField(STRING, API_KEY, OPENWEEATHERMAP_API_KEY)
            buildConfigField(BOOLEAN, USE_MOCK_DATA, FALSE)
            buildConfigField(STRING, MOCK_CURRENT_WEATHER, MOCK_CURRENT_WEATHER_FILE)
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.android.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)

    //Networking
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.mock)

    //Dependency Injection
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compose)
    implementation(libs.koin.android.compat)
    implementation(libs.koin.android.startup)
    implementation(libs.koin.android.navigation)

    //Location Services
    implementation(libs.play.services.location)

    testImplementation(libs.junit)
}