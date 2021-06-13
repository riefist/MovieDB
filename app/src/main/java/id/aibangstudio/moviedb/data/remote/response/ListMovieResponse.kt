package id.aibangstudio.moviedb.data.remote.response
import com.google.gson.annotations.SerializedName
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.utils.BASE_IMAGE_URL


data class ListMovieResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieData> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)

data class MovieData(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
) {
    fun toEntity() = MovieEntity(
        id = id,
        title = title,
        desc = overview,
        posterUrl = BASE_IMAGE_URL + posterPath,
        backdropUrl = BASE_IMAGE_URL + backdropPath
    )
}