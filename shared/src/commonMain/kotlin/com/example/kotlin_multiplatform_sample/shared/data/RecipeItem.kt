package com.example.kotlin_multiplatform_sample.shared.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeItem(
    @SerialName("href")
    val href: String,
    @SerialName("ingredients")
    val ingredients: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String
)