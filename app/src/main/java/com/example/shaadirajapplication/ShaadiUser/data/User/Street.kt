package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Street(
    @SerializedName("name")
    var name: String?,
    @SerializedName("number")
    var number: Int?
)