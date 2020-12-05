package com.example.kotlin_multiplatform_sample.shared.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    @SerialName("href")
    val href: String,
    @SerialName("results")
    val recipeItems: List<RecipeItem>,
    @SerialName("title")
    val title: String,
    @SerialName("version")
    val version: Double
)