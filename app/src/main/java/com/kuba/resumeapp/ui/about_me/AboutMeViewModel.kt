package com.kuba.resumeapp.ui.about_me

import androidx.lifecycle.LiveData
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

    private val _personalInfoLD = MutableLiveData<PersonalInfo>()
    private val _requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    val personalInfoLD: LiveData<PersonalInfo> get() = _personalInfoLD
    val requestStatus: LiveData<RequestStatus> get() = _requestStatus

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetch() {
        _requestStatus.value = RequestStatus.CALLING
        apiService.getPersonalData().enqueue(object : Callback<PersonalInfo> {
            override fun onResponse(call: Call<PersonalInfo>, response: Response<PersonalInfo>) {
                if (response.isSuccessful) {
                    _personalInfoLD.value = response.body()
                    _requestStatus.value = RequestStatus.SUCCESS
                } else {
                    _requestStatus.value = RequestStatus.FAIL
                }
            }

            override fun onFailure(call: Call<PersonalInfo>, t: Throwable) {
                _requestStatus.value = RequestStatus.FAIL
                t.printStackTrace()
            }
        })
    }
}