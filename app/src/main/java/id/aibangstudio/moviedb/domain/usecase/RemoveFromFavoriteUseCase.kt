package id.aibangstudio.moviedb.domain.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.moviedb.core.usecase.CompletableUseCase
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import io.reactivex.Completable

class RemoveFromFavoriteUseCase(
    private val mMovieRepository: MovieRepository,
    mSchedulerProvider: SchedulerProvider
): CompletableUseCase<RemoveFromFavoriteUseCase.Params>(mSchedulerProvider) {

    data class Params(
        val movieId: Int
    )

    override fun execute(params: Params?): Completable {
        requireNotNull(params, { "params cant be null" })
        return mMovieRepository.removeFromFavorite(params.movieId)
    }
}