package ru.kestus.learning_notes.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "memory_cards",
    indices = [Index("cardId")],
)
data class MemoryCardEntity (
    @PrimaryKey(autoGenerate = true)
    val cardId: Long,
    val front: String,
    val back: String,
    val appearanceRate: Double,
) {
    companion object {
        const val DEFAULT_APPEARANCE_RATE = 1.0
    }
}