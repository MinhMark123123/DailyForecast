package m.n.dailyforecast.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import m.n.dailyforecast.common.BaseViewModel
import m.n.dailyforecast.data.AppError
import m.n.dailyforecast.data.Daily
import m.n.dailyforecast.data.ResultViewState
import m.n.dailyforecast.domain.GetForecastUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    var getForecastUseCase: GetForecastUseCase
) : BaseViewModel() {

    private val _resultForecast = MutableLiveData<ResultViewState<List<Daily>>>()
    val listDailyLive: LiveData<ResultViewState<List<Daily>>> = _resultForecast
    var lat: Double? = null
    var lon: Double? = null

    override fun onStart() {
        super.onStart()
        queryForecast(lat, lon)
    }

    private fun queryForecast(lat: Double?, lon: Double?) {
        if (lat == null || lon == null) {
            return
        }
        val params = GetForecastUseCase.Params(lat, lon)
        disposables.addAll(
            getForecastUseCase.invoke(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                    DisposableObserver<List<Daily>>() {
                    override fun onStart() {
                        super.onStart()
                        _resultForecast.postValue(ResultViewState.Loading)
                    }

                    override fun onNext(t: List<Daily>?) {
                        val success: ResultViewState.Success<List<Daily>> =
                            ResultViewState.Success(t ?: arrayListOf())
                        _resultForecast.postValue(success)
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                        _resultForecast.postValue(ResultViewState.Error(AppError(e)))
                    }

                    override fun onComplete() {
                    }
                })
        )
    }

    fun retry() {
        if (lat == null || lon == null) {
            return
        }
        if (_resultForecast.value != null && _resultForecast.value is ResultViewState.Success && (_resultForecast.value as ResultViewState.Success<List<Daily>>).data.isNotEmpty()) {
            return
        }
        if(_resultForecast.value != null && _resultForecast.value is ResultViewState.Loading){
            return
        }
        queryForecast(lat, lon)
    }
}