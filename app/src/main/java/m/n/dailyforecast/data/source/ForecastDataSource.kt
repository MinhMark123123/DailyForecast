package m.n.dailyforecast.data.source

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.data.Daily
import m.n.dailyforecast.data.ForecastResponse

interface ForecastDataSource {
    fun queryForecast(lat: Double, lng: Double): Observable<List<Daily>>
    fun saveForecast(listData: List<Daily>)
}