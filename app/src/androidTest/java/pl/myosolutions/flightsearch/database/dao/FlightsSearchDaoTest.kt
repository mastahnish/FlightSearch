package pl.myosolutions.flightsearch.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pl.myosolutions.flightsearch.database.DatabaseTest
import pl.myosolutions.flightsearch.database.FlightSearchDao
import pl.myosolutions.flightsearch.extensions.getValueBlocking
import pl.myosolutions.flightsearch.models.dto.flights.FlightSearch
import pl.myosolutions.flightsearch.models.dto.flights.raw.*
import pl.myosolutions.flightsearch.models.entities.FlightSearchEntity

@RunWith(AndroidJUnit4::class)
class FlightsSearchDaoTest : DatabaseTest() {

    private lateinit var dao: FlightSearchDao

    @Before
    override fun initDb() {
        super.initDb()
        dao = database.flightSearchDao()
    }


    @Test
    fun `whenUserSearchesSeveralTimes_onlyOneRecentResultIsStoredInDb`() {
        dao.insert(FlightSearchResultProvider.searchResult1)
        dao.insert(FlightSearchResultProvider.searchResult2)
        dao.insert(FlightSearchResultProvider.searchResult3)

        val numberOfElements = dao.all().getValueBlocking()?.size

        assertEquals(1, numberOfElements)
    }
    private object FlightSearchResultProvider {

        private val fare = RawFare(150.0, 10, 1)
        private val regularFare = RawRegularFare("A", listOf(fare))
        private val flight = RawFlight("XY 123", 10, "15:00", regularFare)
        private val flightDate = RawFlightDate("2021-05-12", listOf(flight))
        private val flightTrip = RawFlightTrip("POZ", "Poznan", "ATH", "Athens", listOf(flightDate) )
        private val flightSearch1 = FlightSearch(RawFlightSearch(null, "2020-11-05T09:51:40.328Z", "PLN", listOf(flightTrip) ))
        private val flightSearch2 = FlightSearch(RawFlightSearch(null, "2020-12-05T09:51:40.328Z", "PLN", listOf(flightTrip) ))
        private val flightSearch3 = FlightSearch(RawFlightSearch(null, "2020-13-05T09:51:40.328Z", "PLN", listOf(flightTrip) ))


        val searchResult1 = FlightSearchEntity(flightSearch1)
        val searchResult2 = FlightSearchEntity(flightSearch2)
        val searchResult3 = FlightSearchEntity(flightSearch3)
    }

}