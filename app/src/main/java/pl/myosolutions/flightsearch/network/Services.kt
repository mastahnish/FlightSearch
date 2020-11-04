package pl.myosolutions.flightsearch.network

import android.util.Log
import io.reactivex.Single
import pl.myosolutions.flightsearch.Constants.DESTINATIONS_URL
import pl.myosolutions.flightsearch.models.dto.flights.FlightSearch
import pl.myosolutions.flightsearch.models.dto.places.PlacesResponse
import pl.myosolutions.flightsearch.models.entities.FlightSearchEntity
import pl.myosolutions.flightsearch.models.entities.PlaceEntity
import pl.myosolutions.flightsearch.repository.FlightsRepository
import pl.myosolutions.flightsearch.repository.PlacesRepository

class Services constructor(private val apiServices: ApiServices,
                           private val flightsRepository: FlightsRepository,
                           private val placesRepository: PlacesRepository) {

    fun downloadPlaces() : Single<PlacesResponse> =
        apiServices.fetchDestinations(DESTINATIONS_URL).flatMap {
            val placesResponse = PlacesResponse(it)
            Log.d("Ryanair", "places response: $placesResponse")
            placesRepository.savePlaces(placesResponse.stations.map { station -> PlaceEntity(station) })
            Single.just(placesResponse)
        }

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
            val flightSearchResponse =
                FlightSearch(it)

            flightsRepository.saveSearchResult(FlightSearchEntity(flightSearchResponse))
            Single.just(flightSearchResponse)

        }


}