package m.n.dailyforecast.data.source

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.CityLocation

interface LocationDataSource {
    fun queryLocations(key: String): Observable<List<CityLocation>>
    fun loadAllLocations(): Observable<List<CityLocation>>
    fun saveLocation(listData: List<CityLocation>)
}