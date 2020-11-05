package pl.myosolutions.flightsearch.repository

import androidx.lifecycle.LiveData
import pl.myosolutions.flightsearch.AppExecutors
import pl.myosolutions.flightsearch.database.PlacesDao
import pl.myosolutions.flightsearch.models.entities.PlaceEntity

class PlacesRepository constructor(
        private val executor: AppExecutors,
        private val placesDao:  PlacesDao
    ){

        fun getAllPlaces(): LiveData<List<PlaceEntity>> = placesDao.all()

        fun savePlaces(places: List<PlaceEntity>) {
            executor.diskIO().execute {
                placesDao.run {
                    insert(places)
                }
            }
        }

        fun searchForPlaces(query: String) : LiveData<List<PlaceEntity>> = placesDao.searchForPlaces(query)

}