package pl.myosolutions.flightsearch.inject

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.myosolutions.flightsearch.ui.flights.FlightsViewModel
import pl.myosolutions.flightsearch.ui.search.SearchViewModel

val viewModelModule = module {
    viewModel { FlightsViewModel(get(), get()) }

    viewModel { SearchViewModel(get()) }
}