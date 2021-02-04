package m.n.dailyforecast.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import m.n.dailyforecast.data.CityLocation


@Database(entities = [CityLocation::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun consolidateForecastDao(): AppDao
}