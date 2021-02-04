package m.n.dailyforecast.domain

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.common.UseCase
import m.n.dailyforecast.data.CityLocation
import m.n.dailyforecast.data.source.LocationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationUseCase @Inject constructor(
    var locationRepository: LocationRepository
) : UseCase<String, List<CityLocation>>() {
    override fun invoke(param: String): Observable<List<CityLocation>> {
        return locationRepository.queryLocations(param);
    }
}