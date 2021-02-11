package m.n.dailyforecast.data.source.local

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.CityLocation
import m.n.dailyforecast.data.source.LocationDataSource
import m.n.dailyforecast.data.source.local.db.AppDao
import javax.inject.Inject

class LocationLocalSource @Inject constructor(
    var appDao: AppDao
) : LocationDataSource {
    var listLocation: List<CityLocation> = ArrayList()
    override fun queryLocations(key: String): Observable<List<CityLocation>> {
        val result = listLocation.filter {
            it.components.city?.replace(" ", "")?.contains(key.replace(" ", ""), ignoreCase = true)
                ?: false
        }
        if (result.isEmpty()) {
            return Observable.empty()
        }
        return Observable.just(ArrayList(result))
    }

    override fun loadAllLocations(): Observable<List<CityLocation>> {
        return appDao.queryAllLocation().doOnNext { listLocation = it }
    }

    override fun saveLocation(listData: List<CityLocation>) {
        appDao.insertAllLocation(listData)
    }
}