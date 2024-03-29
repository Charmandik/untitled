package ru.bikbulatov.pureWave.authors.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.bikbulatov.pureWave.R
import ru.bikbulatov.pureWave.authors.models.AuthorModel
import ru.bikbulatov.pureWave.databinding.FragmentSingleAuthorBinding
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastModel
import ru.bikbulatov.pureWave.podcasts.ui.PodcastsViewModel
import ru.bikbulatov.pureWave.podcasts.ui.adapters.PodcastsAdapter

class FragmentSingleAuthor : Fragment() {
    private lateinit var binding: FragmentSingleAuthorBinding
    private lateinit var authorsVM: AuthorsVM
    private lateinit var podcastsViewModel: PodcastsViewModel

    val podcasts: MutableList<PodcastModel> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleAuthorBinding.inflate(inflater, container, false)
        authorsVM = ViewModelProvider(requireActivity()).get(AuthorsVM::class.java)
        podcastsViewModel = ViewModelProvider(requireActivity()).get(PodcastsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnAuthor()

    }

    fun observeOnAuthor() {
        authorsVM.singleAuthor.observe(viewLifecycleOwner, Observer {
            it?.let {
                configureView(it)
                podcastsViewModel.podcastsAllLoadedSize = it.podcasts.size
                for (podcast in it.podcasts) {
                    podcastsViewModel.getPodcast(podcast.id)
                }
                observeOnPodcast()
            }
        })
    }


    fun configureView(authorModel: AuthorModel) {
        binding.authorPreview.tvAuthorName.text = authorModel.name
        binding.authorPreview.tvDescription.text = authorModel.position
        Glide
            .with(requireContext())
            .load(authorModel.photo)
            .centerCrop()
            .transform(CenterCrop(), RoundedCorners(180))
            .into(binding.authorPreview.ivAuthor)
        binding.ivBackBtn.setOnClickListener {
            AnimationUtils.loadAnimation(requireContext(), R.anim.btn_click)
            parentFragmentManager.popBackStack()
        }
    }

    fun observeOnPodcast() {
        podcastsViewModel.singlePodcast.observe(viewLifecycleOwner, Observer {
            podcasts.add(it)
            Log.d("test999", "podcasts loaded size = ${podcasts.size}")
            if (podcasts.size >= podcastsViewModel.podcastsAllLoadedSize) {
                Log.d("test999", "started drawing adapter")
                for (podcast in podcasts) {
                    val tempRecyclerView = RecyclerView(requireContext())
                    tempRecyclerView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    tempRecyclerView.adapter =
                        PodcastsAdapter(
                            podcast.tracks.sortedByDescending { it.createdOn },
                            podcast.title,
                            podcast.id,
                            podcastsViewModel
                        )
                    binding.llSingleAuthorRoot.addView(tempRecyclerView)
                }
//                binding.rvSongs.layoutManager =
//                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//                binding.rvSongs.adapter =
//                    PodcastsAdapter(podcasts, podcastsViewModel)
            }
        })
    }
}