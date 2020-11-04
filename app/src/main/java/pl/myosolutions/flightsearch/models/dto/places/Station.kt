package pl.myosolutions.flightsearch.models.dto.places

import pl.myosolutions.flightsearch.Constants.EMPTY_STRING
import pl.myosolutions.flightsearch.models.dto.places.raw.RawStation

data class Station(val raw: RawStation?) {
    val code: String = raw?.code ?: EMPTY_STRING
    val name: String = raw?.name ?: EMPTY_STRING
    val countryName: String = raw?.countryName ?: EMPTY_STRING
}