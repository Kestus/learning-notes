package ru.kestus.learning_notes.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kestus.learning_notes.data.database.entities.CategoryEntity
import ru.kestus.learning_notes.data.database.entities.MemoryCardEntity

@Database(
    entities = [
        CategoryEntity::class,
        MemoryCardEntity::class,
        CategoryCardXRef::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val CategoriesDAO: CategoriesDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "notes_db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}