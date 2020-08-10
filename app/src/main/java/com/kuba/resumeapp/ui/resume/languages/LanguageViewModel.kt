package com.kuba.resumeapp.ui.resume.languages

import androidx.lifecycle.LiveData
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
    private val _languageListLD = MutableLiveData<List<LanguageInfo>>()
    private val _requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    val languageListLD: LiveData<List<LanguageInfo>> get() = _languageListLD
    val requestStatus: LiveData<RequestStatus> get() = _requestStatus
    init {
        DaggerApiComponent.create().inject(this)
    }

    fun fetchData() {
        _requestStatus.value = RequestStatus.CALLING
        apiService.getLanguagesInfo().enqueue(object : Callback<List<LanguageInfo>> {
            override fun onResponse(
                call: Call<List<LanguageInfo>>,
                response: Response<List<LanguageInfo>>
            ) {
                if (response.isSuccessful) {
                    _languageListLD.value = response.body()
                    _requestStatus.value = RequestStatus.SUCCESS
                } else {
                    _requestStatus.value = RequestStatus.FAIL
                }
            }

            override fun onFailure(call: Call<List<LanguageInfo>>, t: Throwable) {
                _requestStatus.value = RequestStatus.FAIL
                t.printStackTrace()
            }
        })

    }

}