package m.n.dailyforecast.data.source

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.CityLocation
import javax.inject.Inject

class DefaultLocationRepository @Inject constructor(
    var localLocationSrc: LocationDataSource,
    var remoteSrc: LocationDataSource
) : LocationRepository {

    override fun queryAllLocations(): Observable<List<CityLocation>> =
        localLocationSrc.loadAllLocations()

    override fun queryLocations(key: String): Observable<List<CityLocation>> {
        if (key.isEmpty()) {
            return queryAllLocations()
        }
        return localLocationSrc.queryLocations(key)
            .onErrorResumeNext { remoteSrc.queryLocations(key).doAfterNext { saveLocation(it) } }
            .switchIfEmpty(remoteSrc.queryLocations(key).doAfterNext { saveLocation(it) })
    }

    override fun saveLocation(listData: List<CityLocation>) {
        localLocationSrc.saveLocation(listData)
    }
}