package pl.myosolutions.flightsearch.inject

import org.koin.dsl.module
import pl.myosolutions.flightsearch.repository.FlightsRepository

val repositoryModule = module {

    single{ FlightsRepository(get()) }

}