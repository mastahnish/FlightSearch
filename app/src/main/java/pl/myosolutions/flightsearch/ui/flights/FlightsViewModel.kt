package pl.myosolutions.flightsearch.ui.flights

import androidx.lifecycle.LiveData
import pl.myosolutions.flightsearch.models.entities.FlightSearchEntity
import pl.myosolutions.flightsearch.repository.FlightsRepository
import pl.myosolutions.flightsearch.ui.BaseViewModel

class FlightsViewModel constructor(
    private val flightsRepository: FlightsRepository
) : BaseViewModel() {

    fun getFlightSearch() : LiveData<FlightSearchEntity> = flightsRepository.getRecentFlightSearch()

}