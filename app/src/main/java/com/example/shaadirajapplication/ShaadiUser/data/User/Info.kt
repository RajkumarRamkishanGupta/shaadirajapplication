package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: Int?,
    @SerializedName("seed")
    var seed: String?,
    @SerializedName("version")
    var version: String?
)