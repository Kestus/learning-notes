package ru.kestus.learning_notes.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.kestus.learning_notes.databinding.FragmentCategoriesBinding
import ru.kestus.learning_notes.presentation.data.RepositoryImpl
import ru.kestus.learning_notes.presentation.domain.Repository

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CategoriesViewModel>()

    private val repository: Repository by lazy {
        RepositoryImpl()
    }

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
        binding.rvCategories.adapter = adapter
        adapter.submitList(repository.getCategories())
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