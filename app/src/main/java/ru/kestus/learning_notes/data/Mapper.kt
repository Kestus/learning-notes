package ru.kestus.learning_notes.data

import ru.kestus.learning_notes.data.database.entities.CategoryEntity
import ru.kestus.learning_notes.domain.CategoryItem

object Mapper {

    fun CategoryEntityToItem(categoryEntity: CategoryEntity): CategoryItem {
        return CategoryItem(
            id = categoryEntity.categoryId,
            name = categoryEntity.name
        )
    }

    fun CategoryItemToEntity(categoryItem: CategoryItem): CategoryEntity {
        return CategoryEntity(
            categoryId = categoryItem.id,
            name = categoryItem.name
        )
    }
}