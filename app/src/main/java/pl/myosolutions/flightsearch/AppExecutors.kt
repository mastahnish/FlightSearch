package pl.myosolutions.flightsearch

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Global executor pools for the whole application.
 */
open class AppExecutors(
    private val diskIO: Executor
) {

    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor {
        return diskIO
    }
}