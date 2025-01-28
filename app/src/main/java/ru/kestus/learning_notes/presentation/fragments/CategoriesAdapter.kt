package ru.kestus.learning_notes.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kestus.learning_notes.databinding.CardCategoryItemBinding
import ru.kestus.learning_notes.domain.CategoryItem

class CategoriesAdapter : ListAdapter<CategoryItem, CategoriesAdapter.ViewHolder>(CategoryItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: CardCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryItem) {
            binding.categoryName.text = item.name.replaceFirstChar { it.uppercaseChar() }
        }
    }

    class CategoryItemDiffCallback : DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem == newItem
        }
    }
}