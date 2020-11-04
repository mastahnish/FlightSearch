package pl.myosolutions.flightsearch.models.dto.flights.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawFlightDate (

    @SerializedName("dateOut")
    @Expose
    val dateOut : String,
    @SerializedName("flights")
    @Expose
    val flights : List<RawFlight>
)