package ru.bikbulatov.pureWave.podcasts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.bikbulatov.pureWave.databinding.FragmentSinglePodcastBinding
import ru.bikbulatov.pureWave.podcasts.ui.adapters.PodcastsAdapter

class FragmentSinglePodcast : Fragment() {
    private lateinit var binding: FragmentSinglePodcastBinding
    private lateinit var viewModel: PodcastsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSinglePodcastBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PodcastsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPodcast(requireArguments().getInt("id"))
        observeOnPodcasts()
    }

    fun observeOnPodcasts() {
        viewModel.singlePodcast.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvSongs.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvSongs.adapter =
                    PodcastsAdapter(
                        it.tracks.sortedByDescending { it.createdOn },
                        it.title,
                        viewModel
                    )
            }
        })
    }
}