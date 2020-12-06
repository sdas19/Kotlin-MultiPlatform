package com.example.kotlin_multiplatform_sample.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_multiplatform_sample.shared.DataSource
import com.example.kotlin_multiplatform_sample.shared.data.Recipe
import com.example.kotlin_multiplatform_sample.shared.data.brewary.BrewaryResponseItem
import com.example.kotlin_multiplatform_sample.shared.extensions.ResultHandler
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _mainActivityViewState : MutableLiveData<MainActivityViewState> = MutableLiveData()
    val mainActivityViewState : LiveData<MainActivityViewState> = _mainActivityViewState

    private val dataSource by lazy {
        DataSource()
    }
    val staticList by lazy {
        dataSource.getStaticList()
    }

    fun fetchData() {
        _mainActivityViewState.postValue(MainActivityViewState.Loading)
        viewModelScope.launch {
            when(val response = dataSource.fetchListFromNetwork()) {
                is ResultHandler.Success -> run {
                    val recipeList = response.data.toRecipeList()
                    _mainActivityViewState.postValue(MainActivityViewState.SuccessFromNetwork(recipeList))
                }
                is ResultHandler.Error -> run {
                    _mainActivityViewState.postValue(MainActivityViewState.ErrorFromNetwork(response.throwable))
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
}