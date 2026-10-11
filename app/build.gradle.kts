plugins {
    id("com.android.application")
}

android {
    namespace = "com.recall.calculator"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.recall.calculator"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    // One fixed signing key for every build (Android Studio or GitHub), so a new
    // version installs over the old one and your saved memories are kept.
    // Keep this project private: anyone with this key can sign updates for the app.
    signingConfigs {
        create("recall") {
            storeFile = file("recall-signing.keystore")
            storePassword = "recall-calculator"
            keyAlias = "recall"
            keyPassword = "recall-calculator"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("recall")
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("recall")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Personal sideloaded app: skip the Play Store readiness checks on release builds.
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}
