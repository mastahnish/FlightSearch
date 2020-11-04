package pl.myosolutions.flightsearch.inject

import org.koin.dsl.module
import pl.myosolutions.flightsearch.repository.FlightsRepository
import pl.myosolutions.flightsearch.repository.PlacesRepository

val repositoryModule = module {

    single{ FlightsRepository(get(), get()) }

    single{ PlacesRepository(get(), get()) }

}