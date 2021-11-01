package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("cell")
    var cell: String?,
    @SerializedName("dob")
    var dob: Dob?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("gender")
    var gender: String?,
    @SerializedName("id")
    var id: Id?,
    @SerializedName("location")
    var location: Location?,
    @SerializedName("login")
    var login: Login?,
    @SerializedName("name")
    var name: Name?,
    @SerializedName("nat")
    var nat: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("picture")
    var picture: Picture?,
    @SerializedName("registered")
    var registered: Registered?
)