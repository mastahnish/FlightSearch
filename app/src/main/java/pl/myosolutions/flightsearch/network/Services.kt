package pl.myosolutions.flightsearch.network

import io.reactivex.Single
import pl.myosolutions.flightsearch.models.dto.FlightSearch
import pl.myosolutions.flightsearch.models.dto.raw.RawFlightSearch
import pl.myosolutions.flightsearch.models.entities.FlightSearchEntity
import pl.myosolutions.flightsearch.repository.FlightsRepository

class Services constructor(private val apiServices: ApiServices, private val flightsRepository: FlightsRepository) {

    fun searchFlight(
        departureDate: String,
        departurePlace: String,
        destinationPlace: String,
        adultsCount: Int,
        teensCount: Int,
        childrenCount: Int
    ): Single<FlightSearch> =
        apiServices.fetchFlights(
            departureDate,
            departurePlace,
            destinationPlace,
            adultsCount,
            teensCount,
            childrenCount
        ).flatMap {
            val flightSearchResponse = FlightSearch(it)

//            flightsRepository.saveSearchResult(FlightSearchEntity(flightSearchResponse))
            Single.just(flightSearchResponse)

        }


}