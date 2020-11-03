package pl.myosolutions.flightsearch.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.myosolutions.flightsearch.models.dto.FlightSearch
import pl.myosolutions.flightsearch.models.dto.FlightTrip
import pl.myosolutions.flightsearch.models.dto.raw.RawFlightTrip

@Entity(tableName = "flight_search")
class FlightSearchEntity (

    @PrimaryKey
    val serverTimeUTC: String,
    val currency: String,
    val trips: List<FlightTrip>

) {
    constructor(flightSearch: FlightSearch) : this(
        flightSearch.serverTimeUTC,
        flightSearch.currency,
        flightSearch.trips
    )
}