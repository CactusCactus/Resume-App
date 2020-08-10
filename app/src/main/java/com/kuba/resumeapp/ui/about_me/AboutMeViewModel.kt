package com.kuba.resumeapp.ui.about_me

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.model.PersonalInfo
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.data.retrofit.ApiService
import com.kuba.resumeapp.di.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AboutMeViewModel : ViewModel() {
    @Inject
    lateinit var apiService: ApiService

    val personalInfoLD = MutableLiveData<PersonalInfo>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetch() {
        requestStatus.value = RequestStatus.CALLING
        apiService.getPersonalData().enqueue(object : Callback<PersonalInfo> {
            override fun onResponse(call: Call<PersonalInfo>, response: Response<PersonalInfo>) {
                if (response.isSuccessful) {
                    personalInfoLD.value = response.body()
                    requestStatus.value = RequestStatus.SUCCESS
                } else {
                    requestStatus.value = RequestStatus.FAIL
                }
            }

            override fun onFailure(call: Call<PersonalInfo>, t: Throwable) {
                requestStatus.value = RequestStatus.FAIL
                t.printStackTrace()
            }
        })
    }
}