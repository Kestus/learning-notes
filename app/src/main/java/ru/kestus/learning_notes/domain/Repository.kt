package ru.kestus.learning_notes.domain

import androidx.lifecycle.LiveData

interface Repository {
    fun getCategories(): LiveData<List<CategoryItem>>

    suspend fun insertCategory(categoryItem: CategoryItem)
}