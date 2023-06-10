package com.harjot.retrofitpractice

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("users/{id}")
    fun getUsers(@Path("id") path: Int): Call<UsersResponseItem>

    @POST("customer")
    @FormUrlEncoded
    fun addCustomer(@FieldMap fieldMap: HashMap<String, Any>) : Call<UsersResponseItem>
}