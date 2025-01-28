package ru.kestus.learning_notes.data.database

import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["categoryId", "cardId"],
    indices = [Index("categoryId"), Index("cardId")],
)
data class CategoryCardXRef (
    val categoryId: Long,
    val cardId: Long
)