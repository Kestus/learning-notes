package ru.kestus.learning_notes.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "categories",
    indices = [Index("categoryId")],
)
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long,
    val name: String
)