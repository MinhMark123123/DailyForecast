package m.n.dailyforecast.data.source.remote

import io.reactivex.rxjava3.core.Observable
import m.n.dailyforecast.BuildConfig
import m.n.dailyforecast.data.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiForecast {
    @GET("/data/2.5/onecall")
    fun requestForecast(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("exclude") exclude: String = "hourly,current,minutely,alerts",
        @Query("appid") appID: String = BuildConfig.APP_ID
    ): Observable<Response<ForecastResponse>>
}