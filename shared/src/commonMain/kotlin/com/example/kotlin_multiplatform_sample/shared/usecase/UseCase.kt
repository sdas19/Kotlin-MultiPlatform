package com.example.kotlin_multiplatform_sample.shared.usecase

import com.example.kotlin_multiplatform_sample.shared.data.Recipe
import com.example.kotlin_multiplatform_sample.shared.data.RecipeResponse
import com.example.kotlin_multiplatform_sample.shared.data.brewary.BrewaryResponseItem
import com.example.kotlin_multiplatform_sample.shared.extensions.ResultHandler

interface UseCase {
    fun getStaticRecipeList() : List<Recipe>
    suspend fun fetchRecipe(): ResultHandler<List<BrewaryResponseItem>>
}