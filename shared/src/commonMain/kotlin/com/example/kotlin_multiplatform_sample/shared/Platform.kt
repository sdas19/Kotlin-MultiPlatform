package com.example.kotlin_multiplatform_sample.shared

expect class Platform() {
    val platform: String
}