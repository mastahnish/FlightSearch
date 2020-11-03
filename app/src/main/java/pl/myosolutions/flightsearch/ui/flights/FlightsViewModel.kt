package pl.myosolutions.flightsearch.ui.flights

import androidx.lifecycle.LiveData
import pl.myosolutions.flightsearch.models.dto.FlightSearch
import pl.myosolutions.flightsearch.network.Services
import pl.myosolutions.flightsearch.repository.FlightsRepository
import pl.myosolutions.flightsearch.ui.BaseViewModel

class FlightsViewModel constructor(
    private val services: Services,
    private val flightsRepository: FlightsRepository
) : BaseViewModel() {

//    fun getObservableFlightsSearchResult() : LiveData<FlightSearch> = flightsRepository.getFlightSearch()

}