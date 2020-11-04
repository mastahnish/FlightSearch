package pl.myosolutions.flightsearch.models.dto.places.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawStation(
    @SerializedName("code")
    @Expose
    val code: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("countryName")
    @Expose
    val countryName: String
)