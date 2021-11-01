package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Registered(
    @SerializedName("age")
    var age: Int?,
    @SerializedName("date")
    var date: String?
)