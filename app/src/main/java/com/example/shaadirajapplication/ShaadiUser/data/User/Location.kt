package com.example.shaadirajapplication.ShaadiUser.data.User


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("city")
    var city: String?,
    @SerializedName("coordinates")
    var coordinates: Coordinates?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("postcode")
    var postcode: Any?,
    @SerializedName("state")
    var state: String?,
    @SerializedName("street")
    var street: Street?,
    @SerializedName("timezone")
    var timezone: Timezone?
)