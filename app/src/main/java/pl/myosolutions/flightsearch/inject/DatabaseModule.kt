package pl.myosolutions.flightsearch.inject

import org.koin.dsl.module
import pl.myosolutions.flightsearch.database.AppDatabase

val databaseModule = module {
    single { AppDatabase.getInstance(get()) }

    single { get<AppDatabase>().flightSearchDao() }
}