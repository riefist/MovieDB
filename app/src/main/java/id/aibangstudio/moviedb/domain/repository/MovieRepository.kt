package id.aibangstudio.moviedb.domain.repository

import id.aibangstudio.moviedb.domain.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovies(): Single<List<MovieEntity>>
    fun getComingSoonMovies(): Single<List<MovieEntity>>
    fun getMovieDetail(movieId: Int): Single<MovieEntity>
    fun addToFavorite(movie: MovieEntity): Completable
    fun removeFromFavorite(movieId: Int): Completable
    fun getFavoriteMovies(): Observable<List<MovieEntity>>
    fun isFavoriteMovie(movieId: Int): Observable<Boolean>

}