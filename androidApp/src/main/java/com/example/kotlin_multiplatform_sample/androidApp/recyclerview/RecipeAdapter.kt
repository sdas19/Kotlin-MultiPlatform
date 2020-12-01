package com.example.kotlin_multiplatform_sample.androidApp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlin_multiplatform_sample.androidApp.databinding.SingleRecipeModelBinding
import com.example.kotlin_multiplatform_sample.shared.data.Recipe

class RecipeAdapter : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeViewHolder {
        val itemBinding = SingleRecipeModelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bindTo(item)
        }
    }
}