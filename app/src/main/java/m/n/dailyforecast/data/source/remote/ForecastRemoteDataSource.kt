package m.n.dailyforecast.data.source.remote

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.Daily
import m.n.dailyforecast.data.source.ForecastDataSource
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(
    var apiForecast: ApiForecast
) : ForecastDataSource {
    override fun queryForecast(lat: Double, lng: Double): Observable<List<Daily>> {
        return apiForecast.requestForecast(lat, lng).flatMap { Observable.just(it.body()?.daily) }
    }

    override fun saveForecast(listData: List<Daily>) {
    }
}