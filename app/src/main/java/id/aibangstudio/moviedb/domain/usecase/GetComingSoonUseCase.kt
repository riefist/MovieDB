package id.aibangstudio.moviedb.domain.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.moviedb.core.usecase.None
import id.aibangstudio.moviedb.core.usecase.SingleUseCase
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import io.reactivex.Single

class GetComingSoonUseCase(
    private val mMovieRepository: MovieRepository,
    mSchedulerProvider: SchedulerProvider
) : SingleUseCase<List<MovieEntity>, None>(mSchedulerProvider) {
    override fun execute(params: None?): Single<List<MovieEntity>> {
        return mMovieRepository.getComingSoonMovies()
    }
}