package com.example.kotlin_multiplatform_sample.shared

import io.ktor.client.engine.*

expect class Engine() {
    fun provideEngine(): HttpClientEngine
}