package ru.bikbulatov.pureWave

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.bikbulatov.pureWave.databinding.FragmentProgramsBinding

class FragmentPrograms : Fragment() {
    private lateinit var binding: FragmentProgramsBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgramsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return binding.root
    }
}