package com.kuba.resumeapp.ui.resume.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kuba.resumeapp.databinding.FragmentRecyclerSimpleBinding

class ExperienceFragment : Fragment() {
    private lateinit var viewModel: ExperienceViewModel
    private lateinit var adapter: ExperienceAdapter
    private lateinit var binding: FragmentRecyclerSimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ExperienceViewModel::class.java)
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerSimpleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ExperienceAdapter()
        binding.recyclerView.adapter = adapter
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.educationInfoListLD.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExperienceFragment()
    }
}