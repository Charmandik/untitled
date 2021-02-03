package ru.bikbulatov.pureWave.podcasts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
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
        viewModel.singlePodcastId = requireArguments().getInt("id")
        viewModel.getPodcast(viewModel.singlePodcastId)
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
                        "",
                        it.id,
                        viewModel
                    )
                binding.tvTitle.text = it.title
                var tempString = ""
                for (authorPosition in it.author.indices) {
                    if (authorPosition > 0)
                        tempString += "\n"
                    tempString += it.author[authorPosition].name

                }
                binding.tvAuthors.text = tempString
                binding.tvSongsCount.text = "${it.tracks.size} выпусков"

                Glide
                    .with(requireContext())
                    .asBitmap()
                    .load(it.cover)
                    .centerCrop()
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(binding.ivCover)
            }
        })
    }
}