package pl.myosolutions.flightsearch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import pl.myosolutions.flightsearch.models.entities.FlightSearchEntity

@Dao
abstract class FlightSearchDao {

    @Insert(onConflict = REPLACE)
    abstract fun insert(flightSearch: FlightSearchEntity)

    @Query("SELECT * FROM flight_search")
    abstract fun all() : LiveData<List<FlightSearchEntity>>

    @Query("SELECT * FROM flight_search WHERE id = 1")
    abstract fun getRecentFlightSearch() : LiveData<FlightSearchEntity>

}