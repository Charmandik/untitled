package ru.bikbulatov.pureWave.mainWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bikbulatov.pureWave.articles.ui.ArticlesVM
import ru.bikbulatov.pureWave.authors.ui.AuthorsVM
import ru.bikbulatov.pureWave.databinding.FragmentPickerBinding
import ru.bikbulatov.pureWave.podcasts.PodcastsViewModel

class FragmentPicker : Fragment() {
    private lateinit var binding: FragmentPickerBinding
    private lateinit var authorsVM: AuthorsVM
    private lateinit var podcastsVM: PodcastsViewModel
    private lateinit var articlesVM: ArticlesVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPickerBinding.inflate(inflater, container, false)
        authorsVM = ViewModelProvider(requireActivity()).get(AuthorsVM::class.java)
        podcastsVM = ViewModelProvider(requireActivity()).get(PodcastsViewModel::class.java)
        articlesVM = ViewModelProvider(requireActivity()).get(ArticlesVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        podcastsVM.getPodcasts()
        articlesVM.getArticles()
//        authorsVM.getAuthors()
        observeOnAuthors()
        observeOnArticles()
        observeOnPodcasts()
    }

    private fun observeOnAuthors() {
        authorsVM.authorsList.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }

    private fun observeOnArticles() {
        articlesVM.articles.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvAuthors.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvAuthors.adapter = ArticlesBlogAdapter(it)
            }
        })
    }

    private fun observeOnPodcasts() {
        podcastsVM.podcasts.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvPodcasts.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvPodcasts.adapter = PodcastsCategoriesAdapter(it)
            }
        })
    }
}