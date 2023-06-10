package com.harjot.retrofitpractice

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClass {
    lateinit var  retrofit: Retrofit
    lateinit var apiInterface: ApiInterface
    fun getRetrofitClass() : ApiInterface{
        retrofit = Retrofit
            .Builder()
            .baseUrl("http://localhost:3000/admin/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface
    }
}