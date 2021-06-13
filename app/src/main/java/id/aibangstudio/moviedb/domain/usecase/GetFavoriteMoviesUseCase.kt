package id.aibangstudio.moviedb.domain.usecase

import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.moviedb.core.usecase.None
import id.aibangstudio.moviedb.core.usecase.ObservableUseCase
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import io.reactivex.Observable

class GetFavoriteMoviesUseCase(
    private val mMovieRepository: MovieRepository,
    mSchedulerProvider: SchedulerProvider
): ObservableUseCase<List<MovieEntity>, None>(mSchedulerProvider) {

    override fun execute(params: None?): Observable<List<MovieEntity>> {
        return mMovieRepository.getFavoriteMovies()
    }
}