package com.kuba.resumeapp.ui.resume.education

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.model.EducationInfo
import com.kuba.resumeapp.data.model.RequestStatus
import com.kuba.resumeapp.data.retrofit.ApiService
import com.kuba.resumeapp.di.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EducationViewModel : ViewModel() {
    @Inject
    lateinit var apiService: ApiService
    val educationInfoListLD = MutableLiveData<List<EducationInfo>>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchData() {
        requestStatus.value = RequestStatus.CALLING
        apiService.getEducationInfo().enqueue(object : Callback<List<EducationInfo>> {
            override fun onResponse(
                call: Call<List<EducationInfo>>,
                response: Response<List<EducationInfo>>
            ) {
                if (response.isSuccessful) {
                    educationInfoListLD.value = response.body()
                    requestStatus.value = RequestStatus.SUCCESS
                } else {
                    requestStatus.value = RequestStatus.FAIL
                }
            }

            override fun onFailure(call: Call<List<EducationInfo>>, t: Throwable) {
                requestStatus.value = RequestStatus.FAIL
                t.printStackTrace()
            }
        })
    }
}