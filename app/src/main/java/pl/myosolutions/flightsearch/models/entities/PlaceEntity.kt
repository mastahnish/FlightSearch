package pl.myosolutions.flightsearch.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.myosolutions.flightsearch.models.dto.places.Station

@Entity(tableName = "places")
class PlaceEntity (

    @PrimaryKey
    val code: String,
    val name: String,
    val countryName: String

){
    constructor(station: Station) : this(
        station.code,
        station.name,
        station.countryName
    )
}
