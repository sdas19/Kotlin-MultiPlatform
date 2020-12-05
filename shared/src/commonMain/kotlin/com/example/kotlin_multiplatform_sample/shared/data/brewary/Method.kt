package com.example.kotlin_multiplatform_sample.shared.data.brewary


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Method(
    @SerialName("fermentation")
    val fermentation: Fermentation,
    @SerialName("mash_temp")
    val mashTemp: List<MashTemp>,
    @SerialName("twist")
    val twist: String?
)