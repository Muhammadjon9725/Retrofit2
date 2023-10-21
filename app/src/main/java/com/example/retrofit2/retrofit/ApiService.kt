package com.example.retrofit2.retrofit

import com.example.retrofit2.models.MyRequestTodo
import com.example.retrofit2.models.MyTodo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("plan")
    fun getAllTodo():Call<List<MyTodo>>
    @POST("plan/")
    fun addTodo(@Body myRequestTodo: MyRequestTodo):Call<MyTodo>
}