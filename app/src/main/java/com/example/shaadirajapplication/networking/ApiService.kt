package com.example.shaadirajapplication.networking


import com.example.shaadirajapplication.ShaadiUser.data.User.User
import retrofit2.http.*

interface ApiService {

    @GET(".")
    suspend fun getResult(
        @Query("results") value: Int
    ): User

}