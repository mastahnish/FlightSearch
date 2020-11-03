package pl.myosolutions.flightsearch.models.dto

import pl.myosolutions.flightsearch.Constants.EMPTY_STRING
import pl.myosolutions.flightsearch.models.dto.raw.RawFlightTrip

data class FlightTrip(val raw: RawFlightTrip?) {
    val origin : String = raw?.origin ?: EMPTY_STRING
    val originName : String = raw?.originName ?: EMPTY_STRING
    val destination : String = raw?.destination ?: EMPTY_STRING
    val destinationName : String = raw?.destinationName ?: EMPTY_STRING
    val dates : List<FlightDate> =  arrayListOf<FlightDate>().apply {
        raw?.dates?.forEach {
            this.add(FlightDate(it))
        } ?: emptyList<FlightDate>()
    }
}