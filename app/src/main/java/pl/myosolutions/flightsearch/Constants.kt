package pl.myosolutions.flightsearch

import android.util.Log
import java.util.*

object Constants {

    const val API_VERSION = "v4"
    const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val API_BASE_URL = "https://www.ryanair.com/api/booking/$API_VERSION/%s-%s/"
    const val DESTINATIONS_URL = "https://tripstest.ryanair.com/static/stations.json"

    object BaseUrl{
        private val locale: Locale = Locale.getDefault()

        fun getBaseUrl() : String  {
            Log.d("Ryanair","getBaseUrl: $locale")
           return String.format(locale, API_BASE_URL, locale.language, locale.country)
        }
    }

    const val ROOM_DB_NAME: String = "ryanair_flights_search.db"

    const val EMPTY_STRING = ""
    const val EMPTY_INT: Int = 0
    const val EMPTY_DOUBLE: Double = 0.0
}