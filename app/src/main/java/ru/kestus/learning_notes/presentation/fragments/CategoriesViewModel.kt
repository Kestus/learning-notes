package ru.kestus.learning_notes.presentation.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kestus.learning_notes.data.RepositoryImpl
import ru.kestus.learning_notes.domain.CategoryItem
import ru.kestus.learning_notes.domain.Repository
import ru.kestus.learning_notes.domain.useCase.CreateCategoryUseCase

class CategoriesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository by lazy {
        RepositoryImpl(application)
    }

    val categories by lazy {
        repository.getCategories()
    }

    val createCategoryUseCase = CreateCategoryUseCase(repository)

    fun createNewCategory(categoryItem: CategoryItem) = viewModelScope.launch {
        createCategoryUseCase(categoryItem)
    }

}