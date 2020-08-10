package com.kuba.resumeapp.ui.home

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kuba.resumeapp.data.PersonalInfo
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.databinding.FragmentAboutMeBinding
import com.kuba.resumeapp.ui.BaseFragment
import com.stfalcon.imageviewer.StfalconImageViewer
import com.stfalcon.imageviewer.loader.ImageLoader


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
        viewModel.personalInfoLD.observe(viewLifecycleOwner, Observer { info ->
            info?.let {
                checkViewVisibility(it)
            }
        })
        viewModel.requestStatus.observe(viewLifecycleOwner, Observer { status ->
            binding.loadingView.visibility = if (status == RequestStatus.CALLING) VISIBLE else GONE
        })
    }

    private fun checkViewVisibility(info: PersonalInfo) {
        binding.run {
            phoneLayout.visibility = if (!info.phoneNumber.isNullOrBlank()) VISIBLE else GONE
            phoneTitleTV.visibility = if (!info.phoneNumber.isNullOrBlank()) VISIBLE else GONE
            emailLayout.visibility = if (!info.email.isNullOrBlank()) VISIBLE else GONE
            emailTitleTV.visibility = if (!info.email.isNullOrBlank()) VISIBLE else GONE
            linkedInLayout.visibility = if (!info.linkedInUrl.isNullOrBlank()) VISIBLE else GONE
            linkedInTitleTV.visibility = if (!info.linkedInUrl.isNullOrBlank()) VISIBLE else GONE
        }
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

    fun onImageClick() {
        viewModel.personalInfoLD.value?.imageUrl?.let { imageUrl ->
            val imageArr = Array(1) { imageUrl }
            StfalconImageViewer.Builder<String>(context, imageArr, ImageLoader { imageView, image ->
                Glide.with(this).load(image).into(imageView)
            }).show()
        }

    }

    private fun resolveUri(uriString: String?) {
        uriString?.let {
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

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide
            .with(view)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}