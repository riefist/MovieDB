package id.aibangstudio.moviedb.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.aibangstudio.moviedb.domain.entity.MovieEntity

enum class MovieModelType {
    POPULAR, COMING_SOON, FAVORITE
}

@Entity(tableName = "movies")
data class MovieModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String?,
    @ColumnInfo(name = "backdrop_url") val backdropUrl: String?,
    @ColumnInfo(name = "genres") val genres: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "type") val type: MovieModelType
) {
    companion object {
        fun fromEntity(entity: MovieEntity, movieType: MovieModelType): MovieModel = with(entity) {
            return MovieModel(
                0,
                id,
                title,
                desc,
                posterUrl,
                backdropUrl,
                genres.joinToString(separator = ","),
                releaseDate,
                movieType
            )
        }
    }

    fun toEntity(): MovieEntity {
        return MovieEntity(movieId, title, description, posterUrl, backdropUrl).also {
            it.genres = genres.split(",").toList()
            it.releaseDate = releaseDate
        }
    }
}
