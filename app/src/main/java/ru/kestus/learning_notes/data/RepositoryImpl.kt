package ru.kestus.learning_notes.data

import ru.kestus.learning_notes.domain.CategoryItem
import ru.kestus.learning_notes.domain.Repository

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