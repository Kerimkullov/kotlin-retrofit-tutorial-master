package com.example.smarty.api

import com.example.smarty.models.DefaultResponse
import com.example.smarty.models.LoginResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {
    @POST("login/")
    fun userLogin(@Body login: DefaultResponse) : Call<LoginResponse>
}