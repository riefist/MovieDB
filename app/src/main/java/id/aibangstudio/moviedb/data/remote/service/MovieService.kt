package id.aibangstudio.moviedb.data.remote.service

import id.aibangstudio.moviedb.data.remote.response.ListMovieResponse
import id.aibangstudio.moviedb.data.remote.response.MovieDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun getPopularMovies(
        @Query("page") page: Int
    ): Single<ListMovieResponse>

    @GET("discover/movie")
    fun getComingSoonMovies(
        @Query("page") page: Int,
        @Query("year") year: Int
    ): Single<ListMovieResponse>

    @GET("movie/{movie-id}")
    fun getMovieDetail(
        @Path("movie-id") movieId: Int
    ): Single<MovieDetailResponse>

}