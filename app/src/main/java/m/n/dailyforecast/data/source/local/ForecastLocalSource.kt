package m.n.dailyforecast.data.source.local

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.Daily
import m.n.dailyforecast.data.source.ForecastDataSource
import m.n.dailyforecast.data.source.local.db.AppDao
import javax.inject.Inject

class ForecastLocalSource @Inject constructor(
    var appDao: AppDao
) : ForecastDataSource {
    override fun queryForecast(lat: Double, lng: Double): Observable<List<Daily>> {
        return Observable.just(null)
    }

    override fun saveForecast(listData: List<Daily>) {
    }
}