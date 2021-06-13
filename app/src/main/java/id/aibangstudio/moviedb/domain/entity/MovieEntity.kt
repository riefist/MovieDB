package id.aibangstudio.moviedb.domain.entity

data class MovieEntity(
    val id: Int = 0,
    val title: String = "",
    val desc: String = "",
    val posterUrl: String? = "",
    val backdropUrl: String? = ""
){
    var genres : List<String> = listOf()
    var rating: Double = 0.0
    var releaseDate: String = ""

}