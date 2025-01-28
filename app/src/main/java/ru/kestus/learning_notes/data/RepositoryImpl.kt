package ru.kestus.learning_notes.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.kestus.learning_notes.data.database.AppDatabase
import ru.kestus.learning_notes.domain.CategoryItem
import ru.kestus.learning_notes.domain.Repository

class RepositoryImpl(application: Application) : Repository {

    private val db = AppDatabase.getInstance(application)
    private val categoriesDAO = db.CategoriesDAO
    private val mapper = Mapper

    override fun getCategories(): LiveData<List<CategoryItem>> {
        return categoriesDAO.getAllCategories().map { list ->
            list.map {
                mapper.CategoryEntityToItem(it)
            }
        }
    }

    override suspend fun insertCategory(categoryItem: CategoryItem) {
        categoriesDAO.insertNewCategory(mapper.CategoryItemToEntity(categoryItem))
    }
}