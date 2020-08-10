package com.kuba.resumeapp.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.kuba.resumeapp.databinding.FragmentResumeBinding
import com.kuba.resumeapp.ui.BaseFragment

class ResumeFragment : BaseFragment() {

    private lateinit var binding: FragmentResumeBinding
    private lateinit var adapter: ResumeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResumeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ResumeViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = context?.let { adapter.getFragmentName(it, position) }
        }.attach()
    }

    override fun hasActivityToolbar(): Boolean = true
}