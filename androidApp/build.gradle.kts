plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    val ktor_version = "1.4.1"
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation ("androidx.recyclerview:recyclerview:1.1.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.0")
}

android {
    compileSdkVersion(29)
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.kotlin_multiplatform_sample.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        exclude ("META-INF/*.kotlin_module")
    }
}