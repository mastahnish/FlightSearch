package pl.myosolutions.flightsearch.models.dto.flights.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawFare(

    @SerializedName("amount")
    @Expose
    val amount : Double,
    @SerializedName("discountInPercent")
    @Expose
    val discountInPercent : Int,
    @SerializedName("count")
    @Expose
    val count: Int

)