package ru.kestus.learning_notes.presentation.data

import ru.kestus.learning_notes.presentation.domain.CategoryItem
import ru.kestus.learning_notes.presentation.domain.Repository

class RepositoryImpl : Repository {
    override fun getCategories(): List<CategoryItem> {
        val list = mutableListOf<CategoryItem>()
        repeat(10) {
            list.add(
                CategoryItem(
                    it,
                    it.toString()
                )
            )
        }
        return list
    }
}