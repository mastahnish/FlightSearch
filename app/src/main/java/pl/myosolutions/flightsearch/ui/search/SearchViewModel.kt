package pl.myosolutions.flightsearch.ui.search

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.myosolutions.flightsearch.models.dto.flights.FlightSearch
import pl.myosolutions.flightsearch.network.Services
import pl.myosolutions.flightsearch.repository.PlacesRepository
import pl.myosolutions.flightsearch.ui.BaseViewModel

class SearchViewModel constructor(
    private val services: Services,
    private val placesRepository: PlacesRepository
) : BaseViewModel() {

    fun downloadAllPlaces() = services.downloadPlaces().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun searchPlaces(query: String) = placesRepository.searchForPlaces(query)

    fun search(
        departureDate: String,
        departurePlace: String,
        destinationPlace: String,
        adultsCount: Int,
        teensCount: Int,
        childrenCount: Int
    ): Single<FlightSearch> =
        services.searchFlight(
            departureDate,
            departurePlace,
            destinationPlace,
            adultsCount,
            teensCount,
            childrenCount
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}