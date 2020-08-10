package com.kuba.resumeapp.ui.resume.languages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.model.LanguageInfo
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.data.retrofit.ApiService
import com.kuba.resumeapp.di.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LanguageViewModel : ViewModel() {
    @Inject
    lateinit var apiService: ApiService
    val languageListLD = MutableLiveData<List<LanguageInfo>>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchData() {
        requestStatus.value = RequestStatus.CALLING
        apiService.getLanguagesInfo().enqueue(object : Callback<List<LanguageInfo>> {
            override fun onResponse(
                call: Call<List<LanguageInfo>>,
                response: Response<List<LanguageInfo>>
            ) {
                if (response.isSuccessful) {
                    languageListLD.value = response.body()
                    requestStatus.value = RequestStatus.SUCCESS
                } else {
                    requestStatus.value = RequestStatus.FAIL
                }
            }

            override fun onFailure(call: Call<List<LanguageInfo>>, t: Throwable) {
                requestStatus.value = RequestStatus.FAIL
                t.printStackTrace()
            }
        })

    }

}