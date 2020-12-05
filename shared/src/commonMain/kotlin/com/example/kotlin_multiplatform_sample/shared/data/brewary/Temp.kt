package com.example.kotlin_multiplatform_sample.shared.data.brewary


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Temp(
    @SerialName("unit")
    val unit: String,
    @SerialName("value")
    val value: Int
)