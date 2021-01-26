package ru.bikbulatov.pureWave.podcasts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.bikbulatov.pureWave.databinding.FragmentProgramsBinding

class FragmentPodcasts : Fragment() {
    private lateinit var binding: FragmentProgramsBinding
    private lateinit var viewModel: PodcastsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgramsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PodcastsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPodcasts()
        observeOnPodcasts()
    }

    fun observeOnPodcasts() {
        viewModel.podcastsList.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })
    }
}