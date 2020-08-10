package com.kuba.resumeapp.ui.resume

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kuba.resumeapp.R
import com.kuba.resumeapp.ui.resume.education.EducationFragment
import com.kuba.resumeapp.ui.resume.experience.ExperienceFragment
import com.kuba.resumeapp.ui.resume.languages.LanguageFragment

class ResumeViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = PAGE_NUM

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            EDUCATION_POS -> EducationFragment.newInstance()
            EXPERIENCE_POS -> ExperienceFragment.newInstance()
            LANGUAGE_POS -> LanguageFragment.newInstance()
            else -> Fragment()
        }
    }

    fun getFragmentName(context: Context, position: Int): String {
        return when (position) {
            EDUCATION_POS -> return context.getString(R.string.education)
            EXPERIENCE_POS -> return context.getString(R.string.experience)
            LANGUAGE_POS -> return context.getString(R.string.language)
            else -> context.getString(R.string.unknown)
        }
    }

    companion object {
        const val PAGE_NUM = 3
        const val EDUCATION_POS = 0
        const val EXPERIENCE_POS = 1
        const val LANGUAGE_POS = 2
    }

}