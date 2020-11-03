package pl.myosolutions.flightsearch.network

import io.reactivex.Single
import pl.myosolutions.flightsearch.models.dto.raw.RawFlightSearch
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiServices {

    @GET
    fun fetchDestinations(@Url destinationsUrl : String)

    @GET("Availability?ToUs=AGREED&roundtrip=false&flexdaysout=3&flexdaysin=3&flexdaysbeforeout=3&flexdaysbeforein=3")
    fun fetchFlights(@Query("dateout") departureDate: String,
        @Query("origin") departurePlace: String,
        @Query("destination") destinationPlace: String,
        @Query("adt") adultsCount: Int,
        @Query("teen") teensCount: Int,
        @Query("chd") childrenCount: Int): Single<RawFlightSearch>

}