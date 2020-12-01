package com.example.kotlin_multiplatform_sample.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerview()
        setStaticData()
    }

    private fun setUpRecyclerview() {
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
    }

    private fun setStaticData() {
        val adapter = RecipeAdapter()
        recyclerView.adapter = adapter
        adapter.submitList(staticList)
    }
}
