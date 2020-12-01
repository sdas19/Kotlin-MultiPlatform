package com.example.kotlin_multiplatform_sample.shared

import com.example.kotlin_multiplatform_sample.shared.usecase.UseCaseImpl

class DataSource {

    fun getStaticList() = UseCaseImpl().getStaticRecipeList()
}