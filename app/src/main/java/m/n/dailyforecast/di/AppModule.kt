package m.n.dailyforecast.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import m.n.dailyforecast.data.source.*
import m.n.dailyforecast.data.source.local.ForecastLocalSource
import m.n.dailyforecast.data.source.local.LocationLocalSource
import m.n.dailyforecast.data.source.local.db.AppDao
import m.n.dailyforecast.data.source.local.db.AppDatabase
import m.n.dailyforecast.data.source.remote.ApiForecast
import m.n.dailyforecast.data.source.remote.ApiLocation
import m.n.dailyforecast.data.source.remote.ForecastRemoteDataSource
import m.n.dailyforecast.data.source.remote.LocationRemoteDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    private annotation class RemoteDataSource

    @Qualifier
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    private annotation class LocalDataSource

    @Singleton
    @Provides
    fun provideApiLocation(): ApiLocation {
        return Retrofit.Builder()
            .baseUrl("https://api.opencagedata.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiLocation::class.java)
    }

    @Singleton
    @Provides
    fun provideApiForecast(): ApiForecast {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiForecast::class.java)
    }

    @Singleton
    @RemoteDataSource
    @Provides
    fun provideRemoteLocationSource(api: ApiLocation): LocationDataSource {
        return LocationRemoteDataSource(api)
    }

    @Singleton
    @LocalDataSource
    @Provides
    fun provideLocalLocationSource(appDatabase: AppDatabase): LocationDataSource {
        return LocationLocalSource(appDatabase.consolidateForecastDao())
    }

    @Singleton
    @LocalDataSource
    @Provides
    fun provideLocalForecastSource(appDatabase: AppDatabase): ForecastDataSource {
        return ForecastLocalSource(appDatabase.consolidateForecastDao())
    }

    @Singleton
    @RemoteDataSource
    @Provides
    fun provideRemoteForecastSource(api: ApiForecast): ForecastDataSource {
        return ForecastRemoteDataSource(api)
    }


    @Singleton
    @Provides
    fun provideLocationRepository(
        @LocalDataSource localSrc: LocationDataSource,
        @RemoteDataSource remoteSrc: LocationDataSource
    ): LocationRepository {
        return DefaultLocationRepository(localSrc, remoteSrc)
    }

    @Singleton
    @Provides
    fun provideForecastRepository(
        @RemoteDataSource remoteSrc: ForecastDataSource,
        @LocalDataSource localSrc: ForecastDataSource
    ): ForecastRepository {
        return DefaultForecastRepository(localSrc, remoteSrc)
    }


    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()
    }


}