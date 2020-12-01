package com.example.kotlin_multiplatform_sample.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_multiplatform_sample.androidApp.databinding.ActivityMainBinding
import com.example.kotlin_multiplatform_sample.shared.Greeting
import com.example.kotlin_multiplatform_sample.androidApp.recyclerview.RecipeAdapter
import com.example.kotlin_multiplatform_sample.shared.DataSource

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val staticList by lazy {
        DataSource().getStaticList()
    }

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setUpRecyclerview()
        setStaticData()
    }

    private fun setUpRecyclerview() {
        activityMainBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        activityMainBinding.recyclerview.hasFixedSize()
    }

    private fun setStaticData() {
        val adapter = RecipeAdapter()
        activityMainBinding.recyclerview.adapter = adapter
        adapter.submitList(staticList)
    }
}
