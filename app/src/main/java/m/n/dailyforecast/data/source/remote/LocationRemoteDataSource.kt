package m.n.dailyforecast.data.source.remote

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.CityLocation
import m.n.dailyforecast.data.source.LocationDataSource
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    var apiLocation: ApiLocation
) : LocationDataSource {

    override fun queryLocations(key: String): Observable<List<CityLocation>> {
        return apiLocation.requestLocation(key)
            .flatMap {
                Observable.just(it.body()?.cityLocations)
            }
    }

    override fun loadAllLocations(): Observable<List<CityLocation>> {
        return Observable.just(null)
    }

    override fun saveLocation(listData: List<CityLocation>) {
    }
}