package pl.myosolutions.flightsearch.models.dto

import pl.myosolutions.flightsearch.Constants.EMPTY_STRING
import pl.myosolutions.flightsearch.models.dto.raw.RawFlightDate

data class FlightDate(val raw : RawFlightDate?) {
    val dateOut: String = raw?.dateOut ?: EMPTY_STRING
    val flights: List<Flight> = arrayListOf<Flight>().apply {
        raw?.flights?.forEach {
            this.add(Flight(it))
        } ?: emptyList<Flight>()
    }
}