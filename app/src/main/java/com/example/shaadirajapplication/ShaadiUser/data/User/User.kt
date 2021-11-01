package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("info")
    var info: Info?,
    @SerializedName("results")
    var results: List<Result>?
)