package pl.myosolutions.flightsearch.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.myosolutions.flightsearch.models.dto.flights.FlightSearch
import pl.myosolutions.flightsearch.models.dto.flights.FlightTrip

@Entity(tableName = "flight_search")
class FlightSearchEntity (

    @PrimaryKey
    val id: Int = 1,
    val serverTimeUTC: String,
    val currency: String,
    val trips: List<FlightTrip>

) {
    constructor(flightSearch: FlightSearch) : this(
        1,
        flightSearch.serverTimeUTC,
        flightSearch.currency,
        flightSearch.trips
    )
}