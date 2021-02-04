package m.n.dailyforecast.data.source

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.CityLocation

interface LocationRepository {
    fun queryAllLocations(): Observable<List<CityLocation>>
    fun queryLocations(key: String): Observable<List<CityLocation>>
    fun saveLocation(listData: List<CityLocation>)
}