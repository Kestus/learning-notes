package ru.kestus.learning_notes.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.kestus.learning_notes.databinding.DialogCreateCategoryBinding
import ru.kestus.learning_notes.databinding.FragmentCategoriesBinding
import ru.kestus.learning_notes.domain.CategoryItem

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private var _dialogBinding: DialogCreateCategoryBinding? = null
    private val dialogBinding get() = _dialogBinding!!

    private val viewModel: CategoriesViewModel by viewModels()

    private val adapter by lazy {
        CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater)
        _dialogBinding = DialogCreateCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        fabCreateCategory()
    }

    private fun fabCreateCategory() {
        binding.fabAddCategory.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogBinding.root)
                .show()
            dialogBinding.dialogButtonPositive.setOnClickListener {
                val newCategory = CategoryItem(
                    name = dialogBinding.inputCategoryName.text.toString()
                )
                viewModel.createNewCategory(newCategory)
                dialog.dismiss()
            }
            dialogBinding.dialogButtonNegative.setOnClickListener {
                dialog.cancel()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCategories.adapter = adapter
        viewModel.categories.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _dialogBinding = null
    }

    companion object {
        const val NAME: String = "Categories"

        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }
}