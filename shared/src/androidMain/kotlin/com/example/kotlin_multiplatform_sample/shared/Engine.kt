package com.example.kotlin_multiplatform_sample.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.android.*

actual class Engine {
    actual fun provideEngine(): HttpClientEngine {
        return Android.create()
    }
}