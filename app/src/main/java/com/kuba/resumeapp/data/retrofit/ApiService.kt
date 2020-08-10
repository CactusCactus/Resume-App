package com.kuba.resumeapp.data.retrofit

import com.kuba.resumeapp.data.model.EducationInfo
import com.kuba.resumeapp.data.model.ExperienceInfo
import com.kuba.resumeapp.data.model.LanguageInfo
import com.kuba.resumeapp.data.model.PersonalInfo
import com.kuba.resumeapp.di.DaggerApiComponent
import retrofit2.Call
import javax.inject.Inject

class ApiService {
    @Inject
    lateinit var api: ApiInterface

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getPersonalData(): Call<PersonalInfo> {
        return api.getPersonalInfo()
    }

    fun getEducationInfo(): Call<List<EducationInfo>> {
        return api.getEducationInfo()
    }

    fun getLanguagesInfo(): Call<List<LanguageInfo>> {
        return api.getLanguagesInfo()
    }

    fun getExperienceInfo(): Call<List<ExperienceInfo>> {
        return api.getExperienceInfo()
    }
}