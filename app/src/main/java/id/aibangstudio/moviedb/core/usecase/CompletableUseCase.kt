package id.aibangstudio.moviedb.core.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.momakan.utils.scheduler.with
import io.reactivex.Completable

abstract class CompletableUseCase<in Params>(private val mSchedulers: SchedulerProvider) {

    protected abstract fun execute(params: Params? = null): Completable

    operator fun invoke(params: Params? = null) = execute(params).with(mSchedulers)
}