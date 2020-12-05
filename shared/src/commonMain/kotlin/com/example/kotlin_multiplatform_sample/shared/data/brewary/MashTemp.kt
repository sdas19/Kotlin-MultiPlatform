package com.example.kotlin_multiplatform_sample.shared.data.brewary


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MashTemp(
    @SerialName("duration")
    val duration: Int?,
    @SerialName("temp")
    val temp: TempX
)