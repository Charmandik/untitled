package ru.bikbulatov.pureWave.articles.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.bikbulatov.pureWave.R
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
        viewModel.getArticle(requireArguments().getInt("id"))
        configureView()
        binding.ivHeartBtn.setOnClickListener {
            viewModel.toggleLike(requireArguments().getInt("id"))
            viewModel.getArticle(requireArguments().getInt("id"))
        }
    }

    fun observeOnPodcasts() {
        viewModel.singleArticle.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.tvTitle.text = it.title
                binding.tvArticleBody.text = Html.fromHtml(it.content)
                if (it.isLiked) {
                    binding.ivHeartBtn.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.colorPrimary
                        )
                    )

                } else
                    binding.ivHeartBtn.setColorFilter(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.black
                        )
                    )
            }
        })
    }

    fun configureView() {
        binding.ivBackBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}