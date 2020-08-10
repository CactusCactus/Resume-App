package com.kuba.resumeapp.ui.resume.education

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.model.EducationInfo
import com.kuba.resumeapp.data.model.RequestStatus

class EducationViewModel : ViewModel() {
    val educationInfoListLD = MutableLiveData<List<EducationInfo>>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)

    fun fetchData() {
        requestStatus.value = RequestStatus.CALLING
        educationInfoListLD.value = mockData()
        requestStatus.value = RequestStatus.SUCCESS
    }

    private fun mockData(): List<EducationInfo> {
        return listOf(
            EducationInfo("Magister", "Wyższa szkołą robienia hałasu", "1995-2020", null),
            EducationInfo("Inżynier", "Wyższa szkołą robienia ciszy", "1995-1995", null)
        )
    }
}