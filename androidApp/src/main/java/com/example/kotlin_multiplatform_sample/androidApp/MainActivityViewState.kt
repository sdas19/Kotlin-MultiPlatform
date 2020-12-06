package com.example.kotlin_multiplatform_sample.androidApp

import com.example.kotlin_multiplatform_sample.shared.data.Recipe

sealed class MainActivityViewState {
    object Loading : MainActivityViewState()
    class SuccessFromNetwork(val recipeList : List<Recipe>) : MainActivityViewState()
    class ErrorFromNetwork(val throwable : Throwable) : MainActivityViewState()
}