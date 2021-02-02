package ru.bikbulatov.pureWave.podcasts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.databinding.FragmentProgramsCategoriesBinding
import ru.bikbulatov.pureWave.podcasts.ui.adapters.PodcastsCategoriesAdapter

class FragmentPodcastsCategories : Fragment() {
    private lateinit var binding: FragmentProgramsCategoriesBinding
    private lateinit var podcastsVM: PodcastsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgramsCategoriesBinding.inflate(inflater, container, false)
        podcastsVM = ViewModelProvider(requireActivity()).get(PodcastsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        podcastsVM.podcastsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvPodcasts.layoutManager = GridLayoutManager(requireContext(), 2)
                //todo Добавить отступы между элементами
                binding.rvPodcasts.addItemDecoration(HorizontalMarginItemDecoration(
                    requireContext(),
                    R.dimen.podcasts_horizontal_margin
                ))

//                if (binding.rvStories.itemDecorationCount == 0)
//                    binding.rvStories.addItemDecoration(HorizontalMarginItemDecoration(requireContext(), R.dimen.news_item_margin))
                binding.rvPodcasts.adapter =
                    PodcastsCategoriesAdapter(
                        it.sortedBy { it.lastBuildDate }.filter { it.tracksNum > 0 },
                        podcastsVM,
                        parentFragmentManager
                    )

            }
        })
    }
}