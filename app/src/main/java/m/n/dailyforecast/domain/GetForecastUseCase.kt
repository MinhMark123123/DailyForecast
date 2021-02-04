package m.n.dailyforecast.domain

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.common.UseCase
import m.n.dailyforecast.data.Daily
import m.n.dailyforecast.data.source.ForecastRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastUseCase @Inject constructor(
    var forecastRepository: ForecastRepository
) : UseCase<GetForecastUseCase.Params, List<Daily>>() {

    override fun invoke(param: Params): Observable<List<Daily>> {
        return forecastRepository.queryForecast(param.lat, param.lon)
    }

    data class Params(
        var lat: Double = 0.0,
        var lon: Double = 0.0
    )
}
