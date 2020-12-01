package com.example.kotlin_multiplatform_sample.androidApp.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_multiplatform_sample.androidApp.databinding.SingleRecipeModelBinding
import com.example.kotlin_multiplatform_sample.shared.data.Recipe

class RecipeViewHolder(private val itemBinding: SingleRecipeModelBinding) : RecyclerView.ViewHolder(itemBinding.root){

    fun bindTo(recipe: Recipe){
        itemBinding.title.text = recipe.title
        itemBinding.ingredients.text = recipe.ingredients
    }
}