package com.kuba.resumeapp

import com.kuba.resumeapp.data.model.EducationInfo
import com.kuba.resumeapp.data.model.ExperienceInfo
import com.kuba.resumeapp.data.model.LanguageInfo
import com.kuba.resumeapp.data.model.PersonalInfo
import com.kuba.resumeapp.data.retrofit.ApiService
import com.kuba.resumeapp.ui.about_me.AboutMeViewModel
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response

class FetchTests {

    @Mock
    lateinit var apiService: ApiService

    @InjectMocks
    var listViewModel = AboutMeViewModel()

    private var testSingle: List<PersonalInfo>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testPersonalInfoFetch() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<PersonalInfo>
        Mockito.`when`(apiService.getPersonalData()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getPersonalData().execute())
            .thenReturn(Response.success(200, PersonalInfo("x", "x", "x", "x", "x", "x")))
        val response = apiService.getPersonalData().execute()
        Assert.assertTrue(response.isSuccessful)
    }

    @Test
    fun testPersonalInfoFetchFail() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<PersonalInfo>
        Mockito.`when`(apiService.getPersonalData()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getPersonalData().execute())
            .thenReturn(
                Response.error(400, ResponseBody.create(MediaType.parse("img"), "x"))
            )
        val response = apiService.getPersonalData().execute()
        Assert.assertFalse(response.isSuccessful)
    }

    @Test
    fun testEducationFetch() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<EducationInfo>>
        Mockito.`when`(apiService.getEducationInfo()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getEducationInfo().execute())
            .thenReturn(
                Response.success(200, emptyList())
            )
        val response = apiService.getEducationInfo().execute()
        Assert.assertTrue(response.isSuccessful)
    }

    @Test
    fun testEducationFetchFail() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<EducationInfo>>
        Mockito.`when`(apiService.getEducationInfo()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getEducationInfo().execute())
            .thenReturn(
                Response.error(400, ResponseBody.create(MediaType.parse("img"), "x"))
            )
        val response = apiService.getEducationInfo().execute()
        Assert.assertFalse(response.isSuccessful)
    }

    @Test
    fun testExperienceFetch() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<ExperienceInfo>>
        Mockito.`when`(apiService.getExperienceInfo()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getExperienceInfo().execute())
            .thenReturn(
                Response.success(200, emptyList())
            )
        val response = apiService.getExperienceInfo().execute()
        Assert.assertTrue(response.isSuccessful)
    }

    @Test
    fun testExperienceFetchFail() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<ExperienceInfo>>
        Mockito.`when`(apiService.getExperienceInfo()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getExperienceInfo().execute())
            .thenReturn(
                Response.error(400, ResponseBody.create(MediaType.parse("img"), "x"))
            )
        val response = apiService.getExperienceInfo().execute()
        Assert.assertFalse(response.isSuccessful)
    }

    @Test
    fun testLanguagesFetch() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<LanguageInfo>>
        Mockito.`when`(apiService.getLanguagesInfo()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getLanguagesInfo().execute())
            .thenReturn(
                Response.success(200, emptyList())
            )
        val response = apiService.getLanguagesInfo().execute()
        Assert.assertTrue(response.isSuccessful)
    }

    @Test
    fun testLanguagesFetchFail() {
        val mockedCall = Mockito.mock(Call::class.java) as Call<List<LanguageInfo>>
        Mockito.`when`(apiService.getLanguagesInfo()).thenReturn(mockedCall)
        Mockito.`when`(apiService.getLanguagesInfo().execute())
            .thenReturn(
                Response.error(400, ResponseBody.create(MediaType.parse("img"), "x"))
            )
        val response = apiService.getLanguagesInfo().execute()
        Assert.assertFalse(response.isSuccessful)
    }
}