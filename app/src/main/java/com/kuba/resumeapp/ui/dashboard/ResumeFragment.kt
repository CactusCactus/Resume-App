package com.kuba.resumeapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kuba.resumeapp.R
import com.kuba.resumeapp.ui.BaseFragment

class ResumeFragment : BaseFragment() {

    private lateinit var resumeViewModel: ResumeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        resumeViewModel =
                ViewModelProviders.of(this).get(ResumeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_resume, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        resumeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun hasActivityToolbar(): Boolean = true
}