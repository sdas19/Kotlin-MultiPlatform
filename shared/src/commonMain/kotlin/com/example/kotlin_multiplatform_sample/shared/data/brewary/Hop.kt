package com.example.kotlin_multiplatform_sample.shared.data.brewary


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hop(
    @SerialName("add")
    val add: String,
    @SerialName("amount")
    val amount: Amount,
    @SerialName("attribute")
    val attribute: String,
    @SerialName("name")
    val name: String
)