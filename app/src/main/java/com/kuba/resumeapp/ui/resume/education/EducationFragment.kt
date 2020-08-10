package com.kuba.resumeapp.ui.resume.education

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kuba.resumeapp.R
import com.kuba.resumeapp.databinding.FragmentEducationBinding

class EducationFragment : Fragment() {
    private lateinit var viewModel: EducationViewModel
    private lateinit var adapter: EducationAdapter
    private lateinit var binding: FragmentEducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EducationViewModel::class.java)
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEducationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = EducationAdapter()
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
        fun newInstance() = EducationFragment()
    }
}