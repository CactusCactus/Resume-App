package com.kuba.resumeapp.ui.resume.experience.responsibilities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kuba.resumeapp.databinding.FragmentResponsibilitiesBinding
import com.kuba.resumeapp.ui.BaseFragment
import com.kuba.resumeapp.ui.resume.experience.KEY_LIST

class ResponsibilitiesFragment : BaseFragment() {
    private lateinit var binding: FragmentResponsibilitiesBinding
    private lateinit var adapter: ResponsibilitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResponsibilitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ResponsibilitiesAdapter()
        binding.recyclerView.adapter = adapter
        val responsibilityList = arguments?.getStringArrayList(KEY_LIST)
        responsibilityList?.let {
            adapter.setData(it)
        }

    }

    override fun hasActivityToolbar(): Boolean = true
}