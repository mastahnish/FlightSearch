package pl.myosolutions.flightsearch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pl.myosolutions.flightsearch.inject.*

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModules +  databaseModule + networkModules + repositoryModule + viewModelModule)
        }

    }

}