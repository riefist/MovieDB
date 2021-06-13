package id.aibangstudio.moviedb.data.repository

import id.aibangstudio.moviedb.data.db.dao.MovieDao
import id.aibangstudio.moviedb.data.db.entity.MovieModel
import id.aibangstudio.moviedb.data.db.entity.MovieModelType
import id.aibangstudio.moviedb.data.remote.service.MovieService
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Single<List<MovieEntity>> {
        return movieService.getPopularMovies(page = 1)
            .map { response -> response.results.map { it.toEntity() } }
            .doOnSuccess { movies ->
                movieDao.insert(movies.map { MovieModel.fromEntity(it, MovieModelType.POPULAR) })
            }
            .onErrorResumeNext {
                movieDao.findByType(MovieModelType.POPULAR).map { movies -> movies.map { it.toEntity() } }
            }

    }

    override fun getComingSoonMovies(): Single<List<MovieEntity>> {
        return movieService.getComingSoonMovies(1, Calendar.getInstance().get(Calendar.YEAR) + 1)
            .map { response -> response.results.map { it.toEntity() } }
            .doOnSuccess { movies ->
                movieDao.insert(movies.map { MovieModel.fromEntity(it, MovieModelType.COMING_SOON) })
            }
            .onErrorResumeNext {
                movieDao.findByType(MovieModelType.COMING_SOON).map { movies -> movies.map { it.toEntity() } }
            }
    }

    override fun getMovieDetail(movieId: Int): Single<MovieEntity> {
        return movieService.getMovieDetail(movieId).map { it.toEntity() }
            .onErrorResumeNext {
                movieDao.findByMovieId(movieId).map { it.toEntity() }
            }
    }

    override fun addToFavorite(movie: MovieEntity): Completable {
        return Completable.fromCallable {
            movieDao.insert(MovieModel.fromEntity(movie, MovieModelType.FAVORITE))
        }
    }

    override fun removeFromFavorite(movieId: Int): Completable {
        return Completable.fromCallable {
            movieDao.deleteFavoriteMovie(movieId)
        }
    }

    override fun getFavoriteMovies(): Observable<List<MovieEntity>> {
        return movieDao.getFavorites().map { movies -> movies.map { it.toEntity() } }.toObservable()
    }

    override fun isFavoriteMovie(movieId: Int): Observable<Boolean> {
        return movieDao.isFavoriteMovie(movieId).toObservable().map { it.isNotEmpty() }
    }
}