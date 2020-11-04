package pl.myosolutions.flightsearch.repository

import androidx.lifecycle.LiveData
import pl.myosolutions.flightsearch.AppExecutors
import pl.myosolutions.flightsearch.database.FlightSearchDao
import pl.myosolutions.flightsearch.models.entities.FlightSearchEntity

class FlightsRepository constructor(
    private val executor: AppExecutors,
    private val flightSearchDao: FlightSearchDao
){
    fun saveSearchResult(flightSearchEntity: FlightSearchEntity) {
        executor.diskIO().execute {
            flightSearchDao.saveFlightSearch(flightSearchEntity)
        }
    }

    fun getRecentFlightSearch() : LiveData<FlightSearchEntity> = flightSearchDao.getRecentFlightSearch()

}