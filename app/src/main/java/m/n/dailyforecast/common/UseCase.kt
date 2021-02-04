package m.n.dailyforecast.common

import io.reactivex.rxjava3.core.Observable

abstract class UseCase<T, G> {
    abstract fun invoke(param:  T):Observable<G>
}

