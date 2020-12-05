package com.example.kotlin_multiplatform_sample.shared.usecase

import com.example.kotlin_multiplatform_sample.shared.RecipeApi
import com.example.kotlin_multiplatform_sample.shared.data.Recipe
import com.example.kotlin_multiplatform_sample.shared.data.brewary.BrewaryResponseItem
import com.example.kotlin_multiplatform_sample.shared.extensions.ResultHandler
import io.ktor.client.engine.*

class UseCaseImpl(private val engine: HttpClientEngine) : UseCase {

    override fun getStaticRecipeList(): List<Recipe> {
        return listOf(
            Recipe(
                title = "Vegetable-Pasta Oven Omelet",
                ingredients = "tomato, onions, red pepper, garlic, olive oil, zucchini, cream cheese, vermicelli, eggs, parmesan cheese, milk, italian seasoning, salt, black pepper"
            ),
            Recipe(
                title = "Onion and Fresh Herb Omelet with Mixed Greens",
                ingredients = "egg substitute, milk, parsley, thyme, salt, black pepper, eggs, flour, nonstick cooking spray, onions, garlic, salad greens, salad greens, red wine vinegar, olive oil, goat cheese, almonds"
            )
        )
    }

    override suspend fun fetchRecipe(): ResultHandler<List<BrewaryResponseItem>> {
        return RecipeApi(engine).fetchRecipe()
    }
}