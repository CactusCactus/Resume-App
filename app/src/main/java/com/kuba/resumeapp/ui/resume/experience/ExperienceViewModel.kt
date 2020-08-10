package com.kuba.resumeapp.ui.resume.experience

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.model.ExperienceInfo
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.data.retrofit.ApiService
import com.kuba.resumeapp.di.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ExperienceViewModel : ViewModel() {
    @Inject
    lateinit var apiService: ApiService
    val experienceListLD = MutableLiveData<List<ExperienceInfo>>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchData() {
        requestStatus.value = RequestStatus.CALLING
        apiService.getExperienceInfo().enqueue(object : Callback<List<ExperienceInfo>> {
            override fun onResponse(
                call: Call<List<ExperienceInfo>>,
                response: Response<List<ExperienceInfo>>
            ) {
                if (response.isSuccessful) {
                    experienceListLD.value = response.body()
                    requestStatus.value = RequestStatus.SUCCESS
                } else {
                    requestStatus.value = RequestStatus.FAIL
                }
            }

            override fun onFailure(call: Call<List<ExperienceInfo>>, t: Throwable) {
                requestStatus.value = RequestStatus.FAIL
                t.printStackTrace()
            }
        })
    }
}