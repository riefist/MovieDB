package id.aibangstudio.moviedb.domain.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.moviedb.core.usecase.ObservableUseCase
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import io.reactivex.Observable

class CheckIsFavoriteMovieUseCase(
    private val mMovieRepository: MovieRepository,
    mSchedulerProvider: SchedulerProvider
): ObservableUseCase<Boolean, CheckIsFavoriteMovieUseCase.Params>(mSchedulerProvider) {

    data class Params(
        val movieId: Int
    )

    override fun execute(params: Params?): Observable<Boolean> {
        requireNotNull(params, { "params cant be null" })
        return mMovieRepository.isFavoriteMovie(params.movieId)
    }

}