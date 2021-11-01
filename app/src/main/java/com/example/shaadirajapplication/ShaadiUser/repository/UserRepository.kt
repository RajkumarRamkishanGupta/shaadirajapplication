package com.example.shaadirajapplication.repository


import com.example.shaadirajapplication.ShaadiUser.data.User.User
import com.example.shaadirajapplication.networking.ApiService
import com.example.shaadirajapplication.networking.BaseRepository
import com.example.shaadirajapplication.networking.Resource
import kotlinx.coroutines.Dispatchers

class UserRepository  (private val apiService: ApiService) : BaseRepository() {

    suspend fun getResult(value: Int): Resource<User> {
        return safeApiCallWithErrorData(Dispatchers.IO) { apiService.getResult(value) }
    }
}