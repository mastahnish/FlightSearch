package pl.myosolutions.flightsearch.models.dto.places

import pl.myosolutions.flightsearch.models.dto.places.raw.RawPlacesResponse

data class PlacesResponse (val response: RawPlacesResponse) {
    val stations : List<Station> = response.stations?.map { Station(it) }?.toList()
        ?: listOf()
}