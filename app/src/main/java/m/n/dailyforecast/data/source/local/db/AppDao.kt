package m.n.dailyforecast.data.source.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.CityLocation

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLocation(consolidatedWeatherEntities: List<CityLocation>)

    @Query("SELECT * FROM CityLocation ")
    fun queryAllLocation(): Observable<List<CityLocation>>
}