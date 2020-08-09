package com.kuba.resumeapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuba.resumeapp.data.PersonalInfo
import com.kuba.resumeapp.data.model.RequestStatus

class AboutMeViewModel : ViewModel() {
    val personalInfoLD = MutableLiveData<PersonalInfo>()
    val requestStatus = MutableLiveData<RequestStatus>(RequestStatus.NOT_STARTED)


    fun fetch() {
        requestStatus.value = RequestStatus.CALLING
        personalInfoLD.value = mockData();
        requestStatus.value = RequestStatus.SUCCESS
    }

    private fun mockData(): PersonalInfo = PersonalInfo(
        "Jakub Grzeda",
        "https://i.imgur.com/rcyrwM5.jpg",
        "+48 501 666 320",
        "mail@mail.com",
        "https://i.imgur.com/rcyrwM5.jpg",
        "Jestem Kuba i luuuubie se podupczyc"
    )

}