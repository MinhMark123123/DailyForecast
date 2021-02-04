package m.n.dailyforecast.data.source

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.Daily
import javax.inject.Inject

class DefaultForecastRepository @Inject constructor(
    var localSrc: ForecastDataSource,
    var remoteSrc: ForecastDataSource
) : ForecastRepository {
    override fun queryForecast(lat: Double, lng: Double): Observable<List<Daily>> {
        return remoteSrc.queryForecast(lat, lng)
    }

    override fun saveForecast(listData: List<Daily>) {
    }
}