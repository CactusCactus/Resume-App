package com.kuba.resumeapp.data.model

data class ExperienceInfo(
    val company: String?,
    val location: String?,
    val timeFrame: String?,
    val position: String?,
    val responsibilities: List<String>?
)