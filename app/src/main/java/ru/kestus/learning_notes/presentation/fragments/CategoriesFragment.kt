package ru.kestus.learning_notes.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.kestus.learning_notes.R
import ru.kestus.learning_notes.databinding.FragmentCategoriesBinding
import ru.kestus.learning_notes.domain.CategoryItem
import ru.kestus.learning_notes.presentation.view.DialogInputText

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoriesViewModel by viewModels()

    private val adapter by lazy {
        CategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        fabCreateCategory()
    }

    private fun fabCreateCategory() {
        binding.fabAddCategory.setOnClickListener {
            DialogInputText(
                requireContext(),
                title = getString(R.string.create_new_category),
                hint = getString(R.string.category_input_hint)
            ) {
                val input = it.input ?: ""
                if (input.isEmpty()) it.setErrorMessage("Category title should not be empty!")
                else {
                    viewModel.createNewCategory(
                        CategoryItem(
                            name = input
                        )
                    )
                }
            }.show()
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
    }

    companion object {
        const val NAME: String = "Categories"

        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }
}