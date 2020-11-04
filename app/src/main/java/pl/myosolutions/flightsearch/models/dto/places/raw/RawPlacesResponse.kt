package pl.myosolutions.flightsearch.models.dto.places.raw

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawPlacesResponse(
    @SerializedName("stations")
    @Expose
    val stations: List<RawStation>?
) {
}