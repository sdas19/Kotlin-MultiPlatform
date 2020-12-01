package com.example.kotlin_multiplatform_sample.shared.usecase

import com.example.kotlin_multiplatform_sample.shared.data.Recipe

interface UseCase {
    fun getStaticRecipeList() : List<Recipe>
}