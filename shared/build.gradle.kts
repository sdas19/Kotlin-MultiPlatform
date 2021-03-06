import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.4.20"
    id("com.android.library")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {

        //val ktor_version = "1.4.3"
        val ktor_version = "1.4.1"
        val ktor_client_version = "1.0.0"
        val ktor_client_core_ios_version = "1.0.0-beta-4"
        val ktor_client_json_ios_version = "1.0.1"
        val kotlinx_coroutines_version = "1.3.8"
        val kotlinx_serialization_json_version = "1.0.1"
        val ktor_client_json_version = "1.2.2"
        val ktor_client_serialization_native_version = "1.3.1"

        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation ("io.ktor:ktor-client:$ktor_client_version")
                implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$kotlinx_coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinx_serialization_json_version")
                implementation ("io.ktor:ktor-client-json:$ktor_client_json_version")
                implementation ("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation ("io.ktor:ktor-client:$ktor_client_version")
                implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinx_coroutines_version")
                implementation( "io.ktor:ktor-client-android:$ktor_version")
                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")
                implementation ("io.ktor:ktor-client-json:$ktor_client_json_version")
                implementation ("io.ktor:ktor-client-serialization-jvm:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$kotlinx_coroutines_version")
                implementation ("io.ktor:ktor-client-ios:$ktor_version")
                implementation ("io.ktor:ktor-client-core-ios:$ktor_client_core_ios_version")
                implementation ("io.ktor:ktor-client-json-ios:$ktor_client_json_ios_version")
                implementation ("io.ktor:ktor-client-serialization-native:$ktor_client_serialization_native_version")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
    packagingOptions {
        exclude ("META-INF/*.kotlin_module")
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)