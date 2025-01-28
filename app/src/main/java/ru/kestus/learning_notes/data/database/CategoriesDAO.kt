package ru.kestus.learning_notes.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.kestus.learning_notes.data.database.entities.CategoryEntity
import ru.kestus.learning_notes.data.database.entities.MemoryCardEntity

@Dao
interface CategoriesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewMemoryCard(memoryCardEntity: MemoryCardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewCategory(categoryEntity: CategoryEntity)

    @Insert
    suspend fun insertCategoryCardReference(reference: CategoryCardXRef)

    @Transaction
    suspend fun insertNewCardToCategory(memoryCardEntity: MemoryCardEntity, categoryEntity: CategoryEntity) {
        insertNewMemoryCard(memoryCardEntity)
        val reference = CategoryCardXRef(
            memoryCardEntity.cardId,
            categoryEntity.categoryId
        )
        insertCategoryCardReference(reference)
    }

    @Query("SELECT * FROM categories ORDER BY categoryId DESC")
    fun getAllCategories(): LiveData<List<CategoryEntity>>

    @Transaction
    @Query("SELECT * FROM categories WHERE categoryId = :categoryId")
    fun getCardsByCategoryId(categoryId: Long): LiveData<CategoryWithCards>

}