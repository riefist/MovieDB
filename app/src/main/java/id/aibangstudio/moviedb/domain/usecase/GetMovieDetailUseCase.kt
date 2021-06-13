package id.aibangstudio.moviedb.domain.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.moviedb.core.usecase.SingleUseCase
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieDetailUseCase(
    private val mMovieRepository: MovieRepository,
    mSchedulerProvider: SchedulerProvider
): SingleUseCase<MovieEntity, GetMovieDetailUseCase.Params>(mSchedulerProvider) {

    override fun execute(params: Params?): Single<MovieEntity> {
        requireNotNull(params, { "params cant be null" })
        return mMovieRepository.getMovieDetail(params.movieId)
    }

    data class Params(val movieId: Int)

}