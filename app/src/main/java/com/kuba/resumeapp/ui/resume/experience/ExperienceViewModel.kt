package com.kuba.resumeapp.ui.resume.experience

import androidx.lifecycle.LiveData
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
    private val _experienceListLD = MutableLiveData<List<ExperienceInfo>>()
    private val _requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    val experienceListLD: LiveData<List<ExperienceInfo>> get() = _experienceListLD
    val requestStatus: LiveData<RequestStatus> get() = _requestStatus

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchData() {
        _requestStatus.value = RequestStatus.CALLING
        apiService.getExperienceInfo().enqueue(object : Callback<List<ExperienceInfo>> {
            override fun onResponse(
                call: Call<List<ExperienceInfo>>,
                response: Response<List<ExperienceInfo>>
            ) {
                if (response.isSuccessful) {
                    _experienceListLD.value = response.body()
                    _requestStatus.value = RequestStatus.SUCCESS
                } else {
                    _requestStatus.value = RequestStatus.FAIL
                }
            }

            override fun onFailure(call: Call<List<ExperienceInfo>>, t: Throwable) {
                _requestStatus.value = RequestStatus.FAIL
                t.printStackTrace()
            }
        })
    }
}