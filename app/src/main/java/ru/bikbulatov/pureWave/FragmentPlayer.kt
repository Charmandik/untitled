package ru.bikbulatov.pureWave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.bikbulatov.pureWave.databinding.FragmentPlayerBinding

class FragmentPlayer : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnCurrentTrack()
        binding.ivBtnPlay.setOnClickListener {
            (requireActivity() as MainActivity).pausePlayer()
        }
        binding.ivTurnUpVolume.setOnClickListener {
            (requireActivity() as MainActivity).turnUpVolume()
        }
        binding.ivTurnDownVolume.setOnClickListener {
            (requireActivity() as MainActivity).turnDownVolume()
        }
    }

    fun observeOnCurrentTrack() {
        (requireActivity() as MainActivity).getCurrentTrack().observe(viewLifecycleOwner, Observer {
            it?.let {
                //todo where Author?
                binding.composition.tvAuthor.text = ""
                binding.composition.tvCompositionName.text = it.title
            }
        })
    }
}