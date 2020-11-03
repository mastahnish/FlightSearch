package pl.myosolutions.flightsearch.models.dto

import pl.myosolutions.flightsearch.Constants.EMPTY_STRING
import pl.myosolutions.flightsearch.models.dto.raw.RawRegularFare

data class RegularFare (val raw : RawRegularFare?) {
    val fareClass : String = raw?.fareClass ?: EMPTY_STRING
    val fares : List<Fare> = arrayListOf<Fare>().apply {
        raw?.fares?.forEach {
            this.add(Fare(it))
        } ?: emptyList<Fare>()
    }
}