package com.example.kotlin_multiplatform_sample.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_multiplatform_sample.androidApp.databinding.ActivityMainBinding
import com.example.kotlin_multiplatform_sample.shared.Greeting
import com.example.kotlin_multiplatform_sample.androidApp.recyclerview.RecipeAdapter
import com.example.kotlin_multiplatform_sample.shared.data.Recipe

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val adapter = RecipeAdapter()
    private val mainActivityViewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setUpRecyclerview()
        observeViewState()
        //setData(mainActivityViewModel.staticList)
        mainActivityViewModel.fetchData()
    }

    private fun observeViewState() {
        mainActivityViewModel.mainActivityViewState.observe(this, Observer {  state ->
            when(state) {
                is MainActivityViewState.Loading -> {
                    progressBarVisibility(isVisible = true)
                }
                is MainActivityViewState.SuccessFromNetwork -> {
                    progressBarVisibility(isVisible = false)
                    setData(recipeList = state.recipeList)
                }
                is MainActivityViewState.ErrorFromNetwork -> {
                    progressBarVisibility(isVisible = false)
                    showToast(message = state.throwable.message)
                }
            }
        })
    }

    private fun setUpRecyclerview() {
        activityMainBinding.recyclerview.adapter = adapter
        activityMainBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        activityMainBinding.recyclerview.hasFixedSize()
    }

    private fun setData(recipeList: List<Recipe>) {
        activityMainBinding.recyclerview.visibility = VISIBLE
        adapter.submitList(recipeList)
    }

    private fun progressBarVisibility(isVisible : Boolean) {
        if(isVisible) {
            activityMainBinding.progressbar.visibility = VISIBLE
        } else {
            activityMainBinding.progressbar.visibility = GONE
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(this, message ?: "Something went wrong", Toast.LENGTH_LONG).show()
    }
}
