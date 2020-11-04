package pl.myosolutions.flightsearch.models.dto.flights.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawFlightSearch(

    @SerializedName("message")
    @Expose
    val message : String?,
    @SerializedName("currency")
    @Expose
    val currency : String?,
    @SerializedName("serverTimeUTC")
    @Expose
    val serverTimeUTC : String?,
    @SerializedName("trips")
    @Expose
    val trips: List<RawFlightTrip>?

){
    fun isSuccessful(): Boolean = message == null
}