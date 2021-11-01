package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("md5")
    var md5: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("salt")
    var salt: String?,
    @SerializedName("sha1")
    var sha1: String?,
    @SerializedName("sha256")
    var sha256: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("uuid")
    var uuid: String?
)