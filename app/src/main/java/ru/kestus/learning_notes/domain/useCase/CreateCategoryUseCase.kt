package ru.kestus.learning_notes.domain.useCase

import ru.kestus.learning_notes.domain.CategoryItem
import ru.kestus.learning_notes.domain.Repository

class CreateCategoryUseCase(private val repository: Repository) {
    suspend operator fun invoke(categoryItem: CategoryItem) {
        repository.insertCategory(categoryItem)
    }
}