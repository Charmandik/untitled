package ru.bikbulatov.pureWave.authors.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.bikbulatov.pureWave.authors.models.AuthorModel
import ru.bikbulatov.pureWave.databinding.FragmentSingleAuthorBinding

class FragmentSingleAuthor : Fragment() {
    private lateinit var binding: FragmentSingleAuthorBinding
    private lateinit var viewModel: AuthorsVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleAuthorBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(AuthorsVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnAuthor()
    }

    fun observeOnAuthor() {
        viewModel.author.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvSongs.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//                binding.rvSongs.adapter = AuthorsAdapter(it, viewModel, childFragmentManager)
                configureView(it)
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
            .into(binding.authorPreview.ivAuthor)
        binding.ivBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}