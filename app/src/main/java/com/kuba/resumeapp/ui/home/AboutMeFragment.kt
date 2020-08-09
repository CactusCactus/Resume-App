package com.kuba.resumeapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.databinding.FragmentAboutMeBinding
import com.kuba.resumeapp.ui.BaseFragment


class AboutMeFragment : BaseFragment() {

    private lateinit var viewModel: AboutMeViewModel
    private lateinit var binding: FragmentAboutMeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AboutMeViewModel::class.java)
        viewModel.fetch()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutMeBinding.inflate(inflater, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            fragment = this@AboutMeFragment
            viewModel = this@AboutMeFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.requestStatus.observe(viewLifecycleOwner, Observer { status ->
            binding.loadingView.visibility = if (status == RequestStatus.CALLING) VISIBLE else GONE
        })
    }

    fun onPhoneClick() {
        resolveUri("tel:" + viewModel.personalInfoLD.value?.phoneNumber)
    }

    fun onMailClick() {
        resolveUri("mailto:" + viewModel.personalInfoLD.value?.email)
    }

    fun onLinkedInClick() {
        resolveUri(viewModel.personalInfoLD.value?.linkedInUrl)
    }

    private fun resolveUri(uriString: String?) {
        uriString.let {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it)
            val pm = activity?.packageManager
            if (pm != null && intent.resolveActivity(pm) != null) {
                startActivity(intent)
            }
        }
    }


    override fun hasActivityToolbar(): Boolean = false

}