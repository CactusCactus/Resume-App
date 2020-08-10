package com.kuba.resumeapp.data.retrofit

import com.kuba.resumeapp.data.model.EducationInfo
import com.kuba.resumeapp.data.model.ExperienceInfo
import com.kuba.resumeapp.data.model.LanguageInfo
import com.kuba.resumeapp.data.model.PersonalInfo
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("3f8a9346eb377373bcaec574692df0ed/raw/b2fb5172b1fbd59645d672bef1e9f38795880578/personal.json")
    fun getPersonalInfo(): Call<PersonalInfo>

    @GET("56b8d5e375a33e713b3df21251ed561b/raw/9684e4a71adb8b3528ff1e2c9aabdb9fc95458e4/education.json")
    fun getEducationInfo(): Call<List<EducationInfo>>

    @GET("48763b5ae7a7bdd26e95c1a2f9f6fe34/raw/4c65a95bbb80d87dcf8ba6dab13621c49c7dbfb1/languages.json")
    fun getLanguagesInfo(): Call<List<LanguageInfo>>

    @GET("fd7e31c3bb52742dde45e391636fe4bf/raw/d1dfe46e48b1b54f19938d51738195903b9e99a6/experiences.json")
    fun getExperienceInfo(): Call<List<ExperienceInfo>>
}