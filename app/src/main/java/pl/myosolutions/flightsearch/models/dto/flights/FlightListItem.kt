package pl.myosolutions.flightsearch.models.dto.flights

data class FlightListItem (
    val dateOut: String,
    val flightNumber: String,
    val duration: String,
    val amount: Double,
    val currency: String,
    val origin: String,
    val destination: String,
    val infantsLeft: Int,
    val fareClass: String,
    val discountInPercent: Int
)
