@file:Suppress("UNCHECKED_CAST")

package com.kuba.resumeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kuba.resumeapp.data.model.*
import com.kuba.resumeapp.data.retrofit.ApiService
import com.kuba.resumeapp.ui.about_me.AboutMeViewModel
import com.kuba.resumeapp.ui.resume.education.EducationViewModel
import com.kuba.resumeapp.ui.resume.experience.ExperienceViewModel
import com.kuba.resumeapp.ui.resume.languages.LanguageViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call


class ViewModelTests {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @InjectMocks
    var aboutMeViewModel = AboutMeViewModel()

    @InjectMocks
    var experienceViewModel = ExperienceViewModel()

    @InjectMocks
    var educationViewModel = EducationViewModel()

    @InjectMocks
    var languagesViewModel = LanguageViewModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testAboutMeViewModel() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<PersonalInfo>
        Mockito.`when`(apiService.getPersonalData()).thenReturn(mockedCall)
        Assert.assertTrue(aboutMeViewModel.requestStatus.value == RequestStatus.NOT_STARTED)
        aboutMeViewModel.fetch()
        Assert.assertTrue(aboutMeViewModel.requestStatus.value == RequestStatus.CALLING)
    }

    @Test
    fun testExperienceViewModel() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<ExperienceInfo>>
        Mockito.`when`(apiService.getExperienceInfo()).thenReturn(mockedCall)
        Assert.assertTrue(experienceViewModel.requestStatus.value == RequestStatus.NOT_STARTED)
        experienceViewModel.fetchData()
        Assert.assertTrue(experienceViewModel.requestStatus.value == RequestStatus.CALLING)
    }

    @Test
    fun testEducationViewModel() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<EducationInfo>>
        Mockito.`when`(apiService.getEducationInfo()).thenReturn(mockedCall)
        Assert.assertTrue(educationViewModel.requestStatus.value == RequestStatus.NOT_STARTED)
        educationViewModel.fetchData()
        Assert.assertTrue(educationViewModel.requestStatus.value == RequestStatus.CALLING)
    }

    @Test
    fun testLanguageViewModel() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<LanguageInfo>>
        Mockito.`when`(apiService.getLanguagesInfo()).thenReturn(mockedCall)
        Assert.assertTrue(languagesViewModel.requestStatus.value == RequestStatus.NOT_STARTED)
        languagesViewModel.fetchData()
        Assert.assertTrue(languagesViewModel.requestStatus.value == RequestStatus.CALLING)
    }
}