package com.kuba.resumeapp.di

import com.kuba.resumeapp.data.retrofit.ApiInterface
import com.kuba.resumeapp.data.retrofit.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val baseUrl = "https://gist.githubusercontent.com/CactusCactus/"

    @Provides
    fun provideCountriesApi(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }

    @Provides
    fun provideCountriesService(): ApiService {
        return ApiService()
    }

}