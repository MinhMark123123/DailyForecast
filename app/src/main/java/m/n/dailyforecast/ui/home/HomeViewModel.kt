package m.n.dailyforecast.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import m.n.dailyforecast.common.BaseViewModel
import m.n.dailyforecast.data.AppError
import m.n.dailyforecast.data.CityLocation
import m.n.dailyforecast.data.ResultViewState
import m.n.dailyforecast.domain.GetLocationUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var getLocationUseCase: GetLocationUseCase
) : BaseViewModel() {

    private val _resultListCity = MutableLiveData<ResultViewState<List<CityLocation>>>()
    val listCityLive: LiveData<ResultViewState<List<CityLocation>>> = _resultListCity
    private var currentKey = ""
    private val keyQueryResult: PublishSubject<String> = PublishSubject.create()

    init {
        initSearchObservable()
        //search all first
        queryLocation("")
    }

    private fun initSearchObservable() {
        disposables.add(
            keyQueryResult.distinctUntilChanged()
                .switchMap { queryKey -> getLocationUseCase.invoke(queryKey) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(
                    object : DisposableObserver<List<CityLocation>>() {
                        override fun onStart() {
                            _resultListCity.postValue(ResultViewState.Loading)
                        }

                        override fun onNext(t: List<CityLocation>?) {
                            val success: ResultViewState.Success<List<CityLocation>> =
                                ResultViewState.Success(t ?: arrayListOf())
                            _resultListCity.postValue(success)
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                            _resultListCity.postValue(ResultViewState.Error(AppError(e)))
                        }

                        override fun onComplete() {
                        }

                    }
                )
        )
    }

    fun retryQuery() {
        if (currentKey.isEmpty()) {
            return
        }
        if (_resultListCity.value != null && _resultListCity.value is ResultViewState.Success && (_resultListCity.value as ResultViewState.Success<List<CityLocation>>).data.isNotEmpty()) {
            return
        }
        if (_resultListCity.value != null && _resultListCity.value is ResultViewState.Loading) {
            return
        }
        queryLocation(currentKey)
    }

    fun queryLocation(queryKey: String) {
        currentKey = queryKey
        keyQueryResult.onNext(queryKey)
    }


}