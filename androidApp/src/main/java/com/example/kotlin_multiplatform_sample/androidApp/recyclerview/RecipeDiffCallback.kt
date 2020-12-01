package com.example.kotlin_multiplatform_sample.androidApp.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlin_multiplatform_sample.shared.data.Recipe

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame
                (oldItem: Recipe, newItem: Recipe): Boolean
            = oldItem.title == newItem.title

    override fun areContentsTheSame
                (oldItem: Recipe, newItem: Recipe): Boolean
            = oldItem == newItem
}