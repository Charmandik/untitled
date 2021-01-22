package ru.bikbulatov.pureWave.articles.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.bikbulatov.pureWave.databinding.FragmentSingleArticleBinding

class FragmentSingleArticle : Fragment() {
    private lateinit var binding: FragmentSingleArticleBinding
    private lateinit var viewModel: ArticlesVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleArticleBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ArticlesVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnPodcasts()
    }

    fun observeOnPodcasts() {
        viewModel.singleArticle.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }
}