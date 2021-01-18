package ru.bikbulatov.pureWave.articles.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.bikbulatov.pureWave.databinding.FragmentArticlesListBinding

@AndroidEntryPoint
class FragmentArticle : Fragment() {
    private lateinit var binding: FragmentArticlesListBinding
    private lateinit var viewModel: ArticlesVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ArticlesVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticles()
    }

    fun observeOnArticles() {
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvArticles.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvArticles.adapter = ArticlesAdapter()
            }
        })
    }
}