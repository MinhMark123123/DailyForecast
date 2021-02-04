package m.n.dailyforecast.data.source.remote

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.BuildConfig
import m.n.dailyforecast.data.OCLocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiLocation {
    @GET("/geocode/v1/json?pretty=1&no_annotations=1&no_dedupe=1&no_record=1&litmit=1")
    fun requestLocation(@Query("q") query: String, @Query("key") key:String= BuildConfig.LOCATION_KEY): Observable<Response<OCLocationResponse>>

}