package com.example.shaadirajapplication.ShaadiUser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shaadirajapplication.ShaadiUser.data.User.User
import com.example.shaadirajapplication.networking.Resource
import com.example.shaadirajapplication.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val userLiveData: MutableLiveData<Resource<User>> = MutableLiveData()

    fun getUserLiveData(): MutableLiveData<Resource<User>> {
        return userLiveData
    }

    fun getUser(value: Int) {
        userLiveData.value = Resource.loading(null)
        var response: Resource<User>?
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                response = userRepository.getResult(value)
                userLiveData.postValue(response)
            }
        }
    }
}