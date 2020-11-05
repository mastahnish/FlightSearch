package pl.myosolutions.flightsearch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.myosolutions.flightsearch.models.entities.PlaceEntity

@Dao
abstract class PlacesDao {

    @Query("SELECT * FROM places")
    abstract fun all(): LiveData<List<PlaceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(place: PlaceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(items: List<PlaceEntity>)

    @Query("SELECT * FROM places WHERE (code LIKE :query||'%')  OR (name LIKE :query||'%')")
    abstract fun searchForPlaces(query: String): LiveData<List<PlaceEntity>>

}