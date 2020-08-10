package com.kuba.resumeapp.ui.resume.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.databinding.FragmentRecyclerSimpleBinding

class EducationFragment : Fragment() {
    private lateinit var viewModel: EducationViewModel
    private lateinit var adapter: EducationAdapter
    private lateinit var binding: FragmentRecyclerSimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EducationViewModel::class.java)
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
        adapter = EducationAdapter()
        binding.recyclerView.adapter = adapter
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.educationInfoListLD.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                adapter.setData(data)
            }
        })
        viewModel.requestStatus.observe(viewLifecycleOwner, Observer { status ->
            binding.run {
                loadingView.visibility = if (status == RequestStatus.CALLING) VISIBLE else GONE
                errorView.visibility = if (status == RequestStatus.FAIL) VISIBLE else GONE
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = EducationFragment()
    }
}