package com.example.kotlin_multiplatform_sample.shared

import com.example.kotlin_multiplatform_sample.shared.usecase.UseCaseImpl
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.util.*

@KtorExperimentalAPI
class DataSource {

    private val useCase by lazy {
        UseCaseImpl(engine = CIO.create())
    }

    fun getStaticList() = useCase.getStaticRecipeList()

    suspend fun fetchListFromNetwork() = useCase.fetchRecipe()
}