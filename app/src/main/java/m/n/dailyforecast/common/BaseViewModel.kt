package m.n.dailyforecast.common

import androidx.lifecycle.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import m.n.dailyforecast.data.AppError

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    protected val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
        disposables.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

}