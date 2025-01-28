package ru.kestus.learning_notes.domain

data class CategoryItem(
    val id: Long = UNDEFINED_ID,
    val name: String
) {

    companion object {
        private const val UNDEFINED_ID = 0L
    }
}
