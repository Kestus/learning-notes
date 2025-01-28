package ru.kestus.learning_notes.data.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.kestus.learning_notes.data.database.entities.CategoryEntity
import ru.kestus.learning_notes.data.database.entities.MemoryCardEntity


data class CategoryWithCards (
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "cardId",
        associateBy = Junction(CategoryCardXRef::class)
    )
    val memoryCardsList: List<MemoryCardEntity>
)