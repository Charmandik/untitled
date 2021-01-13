package ru.bikbulatov.pureWave.articles.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.bikbulatov.pureWave.databinding.FragmentArticleBinding

@AndroidEntryPoint
class FragmentArticle : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private lateinit var viewModel: ArticlesVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ArticlesVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticles()
    }
}