package pl.myosolutions.flightsearch.models.dto.flights

import pl.myosolutions.flightsearch.Constants.EMPTY_DOUBLE
import pl.myosolutions.flightsearch.Constants.EMPTY_INT
import pl.myosolutions.flightsearch.models.dto.flights.raw.RawFare

data class Fare (val raw : RawFare?) {
    val amount : Double = raw?.amount ?: EMPTY_DOUBLE
    val discountInPercent = raw?.discountInPercent ?: EMPTY_INT
}