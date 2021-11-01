package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Timezone(
    @SerializedName("description")
    var description: String?,
    @SerializedName("offset")
    var offset: String?
)