package pl.myosolutions.flightsearch.models.dto.flights

import pl.myosolutions.flightsearch.Constants.EMPTY_INT
import pl.myosolutions.flightsearch.Constants.EMPTY_STRING
import pl.myosolutions.flightsearch.models.dto.flights.raw.RawFlight

data class Flight (val raw : RawFlight?) {
    val flightNumber : String = raw?.flightNumber ?: EMPTY_STRING
    val infantsLeft : Int = raw?.infantsLeft ?: EMPTY_INT
    val duration : String = raw?.duration ?: EMPTY_STRING
    val regularFare : RegularFare =
        RegularFare(raw?.regularFare)
}