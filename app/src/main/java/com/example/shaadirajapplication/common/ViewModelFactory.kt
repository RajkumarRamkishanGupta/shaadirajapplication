package com.example.shaadirajapplication.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shaadirajapplication.ShaadiUser.viewmodel.UserViewModel
import com.example.shaadirajapplication.networking.ApiService
import com.example.shaadirajapplication.repository.UserRepository

class ViewModelFactory (private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(
                UserRepository(
                    apiService = apiService
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}