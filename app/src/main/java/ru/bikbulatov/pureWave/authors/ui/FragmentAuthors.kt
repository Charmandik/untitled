package ru.bikbulatov.pureWave.authors.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.bikbulatov.pureWave.databinding.FragmentAuthorsBinding

@AndroidEntryPoint
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAuthors()
        observeOnAuthors()
    }

    fun observeOnAuthors() {
        viewModel.authorsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvAuthors.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvAuthors.adapter = AuthorsAdapter(it, viewModel, parentFragmentManager)
            }
        })
    }
}