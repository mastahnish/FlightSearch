package pl.myosolutions.flightsearch.models.dto.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawFlightTrip(

    @SerializedName("origin")
    @Expose
    val origin : String,
    @SerializedName("originName")
    @Expose
    val originName : String,
    @SerializedName("destination")
    @Expose
    val destination : String,
    @SerializedName("destinationName")
    @Expose
    val destinationName : String,
    @SerializedName("dates")
    @Expose
    val dates : List<RawFlightDate>
)
