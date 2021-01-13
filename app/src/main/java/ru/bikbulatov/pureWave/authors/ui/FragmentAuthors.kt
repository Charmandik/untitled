package ru.bikbulatov.pureWave.authors.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.bikbulatov.pureWave.databinding.FragmentAuthorsBinding

class FragmentAuthors : Fragment() {
    private lateinit var binding: FragmentAuthorsBinding
    private lateinit var viewModel: AuthorsVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AuthorsVM::class.java)
        return binding.root
    }
}