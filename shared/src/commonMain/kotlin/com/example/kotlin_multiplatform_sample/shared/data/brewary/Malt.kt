package com.example.kotlin_multiplatform_sample.shared.data.brewary


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Malt(
    @SerialName("amount")
    val amount: AmountX,
    @SerialName("name")
    val name: String
)