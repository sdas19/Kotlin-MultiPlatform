package com.example.kotlin_multiplatform_sample.androidApp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlin_multiplatform_sample.androidApp.R
import com.example.kotlin_multiplatform_sample.shared.data.Recipe

class RecipeAdapter : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_recipe_model, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bindTo(item)
        }
    }
}