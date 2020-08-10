package com.kuba.resumeapp.ui.resume.languages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.model.LanguageInfo
import com.kuba.resumeapp.data.model.Proficiency
import com.kuba.resumeapp.data.model.RequestStatus

class LanguageViewModel : ViewModel() {
    val educationInfoListLD = MutableLiveData<List<LanguageInfo>>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    fun fetchData() {
        requestStatus.value = RequestStatus.CALLING
        educationInfoListLD.value = mockData()
        requestStatus.value = RequestStatus.SUCCESS
    }

    private fun mockData(): List<LanguageInfo> {
        return listOf(
            LanguageInfo("Polski", Proficiency.NATIVE),
            LanguageInfo("English", Proficiency.ADVANCED)
        )
    }
}