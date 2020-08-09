package com.kuba.resumeapp.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.kuba.resumeapp.MainActivity

abstract class BaseFragment : Fragment() {
    abstract fun hasActivityToolbar() : Boolean

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.toggleToolbarVisibility(hasActivityToolbar())
    }
}