package ru.bikbulatov.pureWave.mainWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.articles.ui.ArticlesVM
import ru.bikbulatov.pureWave.articles.ui.FragmentArticles
import ru.bikbulatov.pureWave.authors.ui.AuthorsAdapter
import ru.bikbulatov.pureWave.authors.ui.AuthorsVM
import ru.bikbulatov.pureWave.authors.ui.FragmentAuthors
import ru.bikbulatov.pureWave.databinding.FragmentPickerBinding
import ru.bikbulatov.pureWave.podcasts.ui.FragmentPodcastsCategories
import ru.bikbulatov.pureWave.podcasts.ui.PodcastsViewModel

class FragmentPicker : Fragment() {
    private lateinit var binding: FragmentPickerBinding
    private lateinit var authorsVM: AuthorsVM
    private lateinit var podcastsVM: PodcastsViewModel
    private lateinit var articlesVM: ArticlesVM

    companion object {
        const val TAG = "FragmentPicker"
        const val ITEM_AUTHORS_COUNT = 6
        const val ITEM_ARTICLES_COUNT = 10
        const val ITEM_PODCASTS_COUNT = 10
    }

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
        podcastsVM.getPodcasts()
        articlesVM.getArticles()
        authorsVM.getAuthors()
        observeOnAuthors()
        observeOnArticles()
        observeOnPodcasts()
        configureView()
    }

    private fun observeOnAuthors() {
        authorsVM.authorsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvAuthors.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvAuthors.adapter = AuthorsAdapter(
                    it.sortedBy { it.createdOn.toInt() }.take(ITEM_AUTHORS_COUNT),
                    authorsVM,
                    false,
                    parentFragmentManager
                )
            }
        })
    }

    private fun observeOnArticles() {
        articlesVM.articles.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvArticles.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvArticles.adapter =
                    ArticlesBlogAdapter(
                        it.sortedBy { it.createdOn }.take(ITEM_ARTICLES_COUNT),
                        parentFragmentManager
                    )
            }
        })
    }

    private fun observeOnPodcasts() {
        podcastsVM.podcastsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvPodcasts.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.rvPodcasts.adapter =
                    PodcastsCategoriesAdapter(it.sortedBy { it.lastBuildDate }
                        .take(ITEM_PODCASTS_COUNT))
            }
        })
    }

    fun configureView() {
        binding.tvAuthorsTitle.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.flContainer, FragmentAuthors())
                addToBackStack(FragmentAuthors.TAG)
            }
        }
        binding.tvPodcastsTitle.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.flContainer, FragmentPodcastsCategories())
                addToBackStack(FragmentAuthors.TAG)
            }
        }
        binding.tvArticlesTitle.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.flContainer, FragmentArticles())
                addToBackStack(FragmentAuthors.TAG)
            }
        }

    }
}