package id.aibangstudio.moviedb.presentation.main.fragments.favorite

import androidx.lifecycle.MutableLiveData
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.usecase.GetFavoriteMoviesUseCase
import id.aibangstudio.moviedb.domain.usecase.RemoveFromFavoriteUseCase
import id.aibangstudio.moviedb.presentation.base.BaseViewModel
import id.aibangstudio.moviedb.utils.UiState

class FavoriteViewModel(
    private val mGetFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val mRemoveFromFavoriteUseCase: RemoveFromFavoriteUseCase
): BaseViewModel() {

    var favoriteList = listOf<MovieEntity>()
    val favorites = MutableLiveData<UiState<List<MovieEntity>>>()

    fun getFavorites(){
        compositeDisposable.add(mGetFavoriteMoviesUseCase()
            .subscribe({
                favorites.value = UiState.Success(it)
                favoriteList = it
            },{
                favorites.value = UiState.Error(it)
            }))
    }

    fun removeFromFavorite(movieId: Int){
        val params = RemoveFromFavoriteUseCase.Params(movieId)
        compositeDisposable.add(
            mRemoveFromFavoriteUseCase(params)
                .subscribe({},{})
        )
    }

    fun searchMovie(query: String): List<MovieEntity>{
        val searchQuery = query.lowercase()
        return favoriteList.filter {
            it.title.lowercase().contains(searchQuery) || it.genres.map { it.lowercase() }.contains(searchQuery)
        }
    }
}