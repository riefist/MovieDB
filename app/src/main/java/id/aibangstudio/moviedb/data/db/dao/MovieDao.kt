package id.aibangstudio.moviedb.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import id.aibangstudio.moviedb.data.db.entity.MovieModel
import id.aibangstudio.moviedb.data.db.entity.MovieModelType
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDao: BaseDao<MovieModel> {

    @Query("SELECT * FROM movies WHERE type = :type")
    fun findByType(type: MovieModelType) : Single<List<MovieModel>>

    @Query("SELECT * FROM movies WHERE movie_id = :movieId")
    fun findByMovieId(movieId: Int) : Single<MovieModel>

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getFavorites(type: MovieModelType = MovieModelType.FAVORITE) : Flowable<List<MovieModel>>

    @Query("DELETE FROM movies WHERE movie_id = :movieId ")
    fun deleteFavoriteMovie(movieId: Int)

    @Query("SELECT * FROM movies WHERE movie_id = :movieId AND type = :type")
    fun isFavoriteMovie(movieId: Int, type: MovieModelType = MovieModelType.FAVORITE) : Flowable<List<MovieModel>>

    @Query("SELECT * FROM movies WHERE type = :type AND (title LIKE :search OR genres LIKE :search)")
    fun searchFavoriteMovie(search: String, type: MovieModelType = MovieModelType.FAVORITE): Single<List<MovieModel>>

}