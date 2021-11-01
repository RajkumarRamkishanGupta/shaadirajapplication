package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("name")
    var name: String?,
    @SerializedName("value")
    var value: Any?
)