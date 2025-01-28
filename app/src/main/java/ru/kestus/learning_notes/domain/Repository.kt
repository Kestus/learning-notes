package ru.kestus.learning_notes.domain

interface Repository {
    fun getCategories(): List<CategoryItem>
}