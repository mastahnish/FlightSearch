package pl.myosolutions.flightsearch.ui.search

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.myosolutions.flightsearch.models.dto.FlightSearch
import pl.myosolutions.flightsearch.network.Services
import pl.myosolutions.flightsearch.ui.BaseViewModel

class SearchViewModel constructor(
    private val services: Services
) : BaseViewModel() {

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