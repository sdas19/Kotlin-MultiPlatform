package com.example.kotlin_multiplatform_sample.shared

import com.example.kotlin_multiplatform_sample.shared.usecase.UseCaseImpl
import io.ktor.client.engine.*

class DataSource {

    fun getStaticList(engine: HttpClientEngine) = UseCaseImpl(engine).getStaticRecipeList()
}