package com.kuba.resumeapp.ui.resume

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuba.resumeapp.R
import com.kuba.resumeapp.ui.resume.education.EducationFragment

class ResumeViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = PAGE_NUM

    override fun createFragment(position: Int): Fragment {
        when (position) {
            EDUCATION_POS -> return EducationFragment.newInstance()
        }
        return Fragment()
    }

    fun getFragmentName(context: Context, position: Int): String {
        when (position) {
            EDUCATION_POS -> return context.getString(R.string.education)
        }
        return ""
    }

    companion object {
        const val PAGE_NUM = 3
        const val EDUCATION_POS = 0
    }

}