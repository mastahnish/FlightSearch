package pl.myosolutions.flightsearch.models.dto.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawRegularFare(

    @SerializedName("fareClass")
    @Expose
    val fareClass: String,
    @SerializedName("fares")
    @Expose
    val fares: List<RawFare>

)
