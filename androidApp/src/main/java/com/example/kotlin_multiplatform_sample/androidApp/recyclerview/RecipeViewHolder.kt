package com.example.kotlin_multiplatform_sample.androidApp.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_multiplatform_sample.androidApp.R
import com.example.kotlin_multiplatform_sample.shared.data.Recipe

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bindTo(recipe: Recipe){
        val title: TextView = itemView.findViewById(R.id.title)
        val ingredients: TextView = itemView.findViewById(R.id.ingredients)
        title.text = recipe.title
        ingredients.text = recipe.ingredients
    }
}