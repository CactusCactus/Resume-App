package com.kuba.resumeapp.ui.resume.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.kuba.resumeapp.R
import com.kuba.resumeapp.data.model.ExperienceInfo
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.databinding.FragmentRecyclerSimpleBinding
import java.util.*

public const val KEY_LIST = "KEY_LIST"

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
        setupAdapter()
        binding.recyclerView.adapter = adapter
        observeViewModel()
    }

    private fun setupAdapter() {
        adapter = ExperienceAdapter()
        adapter.listener = object : ExperienceItemListener {
            override fun onClick(model: ExperienceInfo) {
                model.responsibilities?.let {
                    val args = Bundle()
                    args.putStringArrayList(KEY_LIST, ArrayList(it))
                    NavHostFragment.findNavController(this@ExperienceFragment)
                        .navigate(R.id.actionGoToResponsibilities, args)
                }
            }
        }

    }

    private fun observeViewModel() {
        viewModel.experienceListLD.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                adapter.setData(data)
            }
        })
        viewModel.requestStatus.observe(viewLifecycleOwner, Observer { status ->
            binding.run {
                loadingView.visibility =
                    if (status == RequestStatus.CALLING) View.VISIBLE else View.GONE
                errorView.visibility = if (status == RequestStatus.FAIL) View.VISIBLE else View.GONE
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExperienceFragment()
    }
}