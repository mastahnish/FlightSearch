package pl.myosolutions.flightsearch.inject

import org.koin.dsl.module
import pl.myosolutions.flightsearch.AppExecutors

val appModules = module {

    factory { AppExecutors() }

}