package com.kuba.resumeapp.ui.resume.experience

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.model.ExperienceInfo
import com.kuba.resumeapp.data.model.RequestStatus

class ExperienceViewModel : ViewModel() {
    val educationInfoListLD = MutableLiveData<List<ExperienceInfo>>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    fun fetchData() {
        requestStatus.value = RequestStatus.CALLING
        educationInfoListLD.value = mockData()
        requestStatus.value = RequestStatus.SUCCESS
    }

    private fun mockData(): List<ExperienceInfo> {
        return listOf(
            ExperienceInfo("Comarch S.A", "Cracow, Poland", "1995-2020", "Developer", null),
            ExperienceInfo("Comarch S.A", "Cracow, Poland", "1995-2020", "Developer", null)
        )
    }
}