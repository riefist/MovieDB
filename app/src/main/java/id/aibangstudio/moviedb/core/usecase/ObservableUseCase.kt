package id.aibangstudio.moviedb.core.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.momakan.utils.scheduler.with
import io.reactivex.Observable

abstract class ObservableUseCase<T, in Params>(private val mSchedulers: SchedulerProvider) {

    protected abstract fun execute(params: Params? = null): Observable<T>

    operator fun invoke(params: Params? = null) = execute(params).with(mSchedulers)
}