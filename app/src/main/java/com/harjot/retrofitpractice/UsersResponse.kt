package com.harjot.retrofitpractice


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponseItem(
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "contact")
    val contact: Int?=null,
    @Json(name = "password")
    val password: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "gender")
    val gender: String? = null,
)
