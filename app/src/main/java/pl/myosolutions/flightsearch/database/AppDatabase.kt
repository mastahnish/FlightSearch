package pl.myosolutions.flightsearch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.myosolutions.flightsearch.Constants
import pl.myosolutions.flightsearch.models.entities.FlightSearchEntity

@Database(
    entities = [
    FlightSearchEntity::class
    ], version = 1, exportSchema = true
)

@TypeConverters(ListFlightTripConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun flightSearchDao() : FlightSearchDao

    companion object {

        @Volatile
        private var instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                var appInstance = instance
                appInstance ?: buildDatabase(context).also { instance = it }
            }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                Constants.ROOM_DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }



}