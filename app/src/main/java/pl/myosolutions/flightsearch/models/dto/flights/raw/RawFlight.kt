package pl.myosolutions.flightsearch.models.dto.flights.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawFlight(

    @SerializedName("flightNumber")
    @Expose
    val flightNumber: String,
    @SerializedName("infantsLeft")
    @Expose
    val infantsLeft : Int,
    @SerializedName("duration")
    @Expose
    val duration: String,
    @SerializedName("regularFare")
    @Expose
    val regularFare : RawRegularFare

)