package com.example.kotlin_multiplatform_sample.shared.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T : Any> runInDifferentCoroutine(work: suspend () -> T): ResultHandler<T> = withContext(Dispatchers.Main) {
    try {
        val result = work()
        withContext(Dispatchers.Main) {
            when (result) {
                is Throwable -> ResultHandler.Error(result)
                else -> ResultHandler.Success(result)
            }
        }
    } catch (ex: Exception) {
        withContext(Dispatchers.Main) {
            ResultHandler.Error(ex)
        }
    }
}

sealed class ResultHandler<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultHandler<T>()
    data class Error(val throwable: Throwable) : ResultHandler<Nothing>()
}