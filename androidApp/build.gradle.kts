plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    val ktor_version = "1.4.1"
    val activity_ktx_version = "1.1.0"
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
    implementation ("androidx.recyclerview:recyclerview:1.1.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation ("androidx.activity:activity-ktx:$activity_ktx_version")
}

android {
    compileSdkVersion(29)
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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