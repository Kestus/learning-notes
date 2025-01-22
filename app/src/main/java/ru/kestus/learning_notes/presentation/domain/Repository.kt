package ru.kestus.learning_notes.presentation.domain

interface Repository {
    fun getCategories(): List<CategoryItem>
}