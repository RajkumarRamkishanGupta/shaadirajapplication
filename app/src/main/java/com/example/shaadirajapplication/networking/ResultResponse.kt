package com.tatadigital.qmin.networking

import com.google.gson.annotations.SerializedName

data class ResultResponse<T>(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: T
)