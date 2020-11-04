package pl.myosolutions.flightsearch.inject

import org.koin.dsl.module
import pl.myosolutions.flightsearch.network.ApiServices
import pl.myosolutions.flightsearch.network.RestApi
import pl.myosolutions.flightsearch.network.Services

val networkModules = module{

    single{ RestApi.buildRetrofit()
            .create(ApiServices::class.java) }

    factory { Services(get(), get(), get()) }
}




