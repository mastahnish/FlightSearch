package pl.myosolutions.flightsearch.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import pl.myosolutions.flightsearch.extensions.fromJson
import pl.myosolutions.flightsearch.models.dto.flights.FlightTrip

class ListFlightTripConverter {

    @TypeConverter
    fun listToJson(value: List<FlightTrip>?): String =
        Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): List<FlightTrip>? =
        Gson().fromJson(value)


}