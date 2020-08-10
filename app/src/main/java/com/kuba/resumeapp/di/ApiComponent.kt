package com.kuba.resumeapp.di

import com.kuba.resumeapp.data.retrofit.ApiService
import com.kuba.resumeapp.ui.about_me.AboutMeViewModel
import com.kuba.resumeapp.ui.resume.education.EducationViewModel
import com.kuba.resumeapp.ui.resume.experience.ExperienceViewModel
import com.kuba.resumeapp.ui.resume.languages.LanguageViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: ApiService)
    fun inject(aboutMeViewModel: AboutMeViewModel)
    fun inject(educationViewModel: EducationViewModel)
    fun inject(languageViewModel: LanguageViewModel)
    fun inject(experienceViewModel: ExperienceViewModel)
}