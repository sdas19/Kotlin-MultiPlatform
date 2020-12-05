package com.example.kotlin_multiplatform_sample.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_multiplatform_sample.androidApp.databinding.ActivityMainBinding
import com.example.kotlin_multiplatform_sample.shared.Greeting
import com.example.kotlin_multiplatform_sample.androidApp.recyclerview.RecipeAdapter
import com.example.kotlin_multiplatform_sample.shared.DataSource
import com.example.kotlin_multiplatform_sample.shared.data.Recipe
import com.example.kotlin_multiplatform_sample.shared.data.RecipeResponse
import com.example.kotlin_multiplatform_sample.shared.data.brewary.BrewaryResponseItem
import com.example.kotlin_multiplatform_sample.shared.extensions.ResultHandler
import com.example.kotlin_multiplatform_sample.shared.usecase.UseCaseImpl
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.util.*
import kotlinx.coroutines.*
import kotlin.Exception

fun greet(): String {
    return Greeting().greeting()
}

@InternalAPI
class MainActivity : AppCompatActivity() {

    private val staticList by lazy {
        DataSource().getStaticList(createHttpEngine())
    }

    private val useCase by lazy {
        UseCaseImpl(createHttpEngine())
    }

    private lateinit var activityMainBinding: ActivityMainBinding
    private val adapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setUpRecyclerview()
        //setData(staticList)
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.Main).launch {
            when(val response = useCase.fetchRecipe()) {
                is ResultHandler.Success -> run {
                    val recipeList = response.data.toRecipeList()
                    setData(recipeList)
                }
                is ResultHandler.Error -> run {
                    Log.i("Exception", response.throwable.message ?: "")
                }
            }
        }
    }

    private fun List<BrewaryResponseItem>.toRecipeList() : List<Recipe> {
        val recipeList = mutableListOf<Recipe>()
        this.map { item ->
            recipeList.add(Recipe(item.name, item.description))
        }
        return recipeList
    }

    private fun setUpRecyclerview() {
        activityMainBinding.recyclerview.adapter = adapter
        activityMainBinding.recyclerview.layoutManager = LinearLayoutManager(this)
        activityMainBinding.recyclerview.hasFixedSize()
    }

    private fun setData(staticList: List<Recipe>) {
        adapter.submitList(staticList)
    }

    private fun createHttpEngine(): HttpClientEngine {
        return OkHttpEngine(OkHttpConfig())
    }
}
