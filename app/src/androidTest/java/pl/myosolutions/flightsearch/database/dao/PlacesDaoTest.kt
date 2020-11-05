package pl.myosolutions.flightsearch.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pl.myosolutions.flightsearch.database.DatabaseTest
import pl.myosolutions.flightsearch.database.PlacesDao
import pl.myosolutions.flightsearch.extensions.getValueBlocking
import pl.myosolutions.flightsearch.models.entities.PlaceEntity

@RunWith(AndroidJUnit4::class)
class PlacesDaoTest : DatabaseTest() {

    private lateinit var dao: PlacesDao

    @Before
    override fun initDb() {
        super.initDb()
        dao = database.placesDao()
    }


    @Test
    fun whenAddPlacesOneByOne_shouldGetProperSize() {
        dao.insert(PlaceEntity("POZ", "Poznan", "Poland"))
        dao.insert(PlaceEntity("LTN", "London Luton", "United Kingdom"))
        dao.insert(PlaceEntity("ATH", "Athens", "Greece"))

        val numberOfElements = dao.all().getValueBlocking()?.size

        TestCase.assertEquals(3, numberOfElements)
    }

    @Test
    fun whenAddPlacesAsList_shouldGetProperSize() {
        dao.insert(listOf(
            PlaceEntity("POZ", "Poznan", "Poland"),
            PlaceEntity("LTN", "London Luton", "United Kingdom"),
            PlaceEntity("ATH", "Athens", "Greece"))
        )

        val numberOfElements = dao.all().getValueBlocking()?.size

        TestCase.assertEquals(3, numberOfElements)
    }

    @Test
    fun whenSearchPlaceMatchesStartingLettersForCodeAndName_numberOfResultsSizeIsCorrect() {
        dao.insert(listOf(
            PlaceEntity("POZ", "Poznan", "Poland"),
            PlaceEntity("LTN", "London Luton", "United Kingdom"),
            PlaceEntity("ATH", "Athens", "Greece"),
            PlaceEntity("LGW", "London Gatwick", "United Kingdom"))
        )

        val results1 = dao.searchForPlaces("o").getValueBlocking()?.size

        TestCase.assertEquals(0, results1)

        val results2 = dao.searchForPlaces("p").getValueBlocking()?.size

        TestCase.assertEquals(1, results2)

        val results3 = dao.searchForPlaces("l").getValueBlocking()?.size

        TestCase.assertEquals(2, results3)
    }

}