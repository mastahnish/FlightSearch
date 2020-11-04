package pl.myosolutions.flightsearch.models.dto.flights

import pl.myosolutions.flightsearch.Constants.EMPTY_STRING
import pl.myosolutions.flightsearch.models.dto.flights.raw.RawFlightSearch

data class FlightSearch (val raw : RawFlightSearch) {
    val message: String? = raw.message
    val currency: String = raw.currency ?: EMPTY_STRING
    val serverTimeUTC: String = raw.serverTimeUTC ?: EMPTY_STRING
    val trips: List<FlightTrip> = arrayListOf<FlightTrip>().apply {
        raw?.trips?.forEach {
            this.add(
                FlightTrip(
                    it
                )
            )
        } ?: emptyList<FlightTrip>()
    }

    fun isSuccessful() : Boolean = message == null
}

