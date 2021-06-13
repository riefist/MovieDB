package id.aibangstudio.moviedb.domain.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.moviedb.core.usecase.CompletableUseCase
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import io.reactivex.Completable

class AddToFavoriteUseCase(
    private val mMovieRepository: MovieRepository,
    mSchedulerProvider: SchedulerProvider
): CompletableUseCase<AddToFavoriteUseCase.Params>(mSchedulerProvider) {

    data class Params(
        val movie: MovieEntity
    )

    override fun execute(params: Params?): Completable {
        requireNotNull(params, { "params cant be null" })
        return mMovieRepository.addToFavorite(params.movie)
    }
}