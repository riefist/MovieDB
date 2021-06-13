package id.aibangstudio.moviedb.presentation.detail

import androidx.lifecycle.MutableLiveData
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.usecase.AddToFavoriteUseCase
import id.aibangstudio.moviedb.domain.usecase.CheckIsFavoriteMovieUseCase
import id.aibangstudio.moviedb.domain.usecase.GetMovieDetailUseCase
import id.aibangstudio.moviedb.domain.usecase.RemoveFromFavoriteUseCase
import id.aibangstudio.moviedb.presentation.base.BaseViewModel
import id.aibangstudio.moviedb.utils.UiState

class MovieDetailViewModel(
    private val mGetMovieDetailUseCase: GetMovieDetailUseCase,
    private val mAddToFavoriteUseCase: AddToFavoriteUseCase,
    private val mRemoveFromFavoriteUseCase: RemoveFromFavoriteUseCase,
    private val mCheckIsFavoriteMovieUseCase: CheckIsFavoriteMovieUseCase
): BaseViewModel() {

    val detailResult = MutableLiveData<UiState<MovieEntity>>()
    val isFavorite = MutableLiveData<Boolean>()

    fun getMovieDetail(movieId: Int){
        val params = GetMovieDetailUseCase.Params(movieId)
        compositeDisposable.add(
            mGetMovieDetailUseCase(params)
                .subscribe({
                    detailResult.value = UiState.Success(it)
                },{
                    detailResult.value = UiState.Error(it)
                })
        )
    }

    fun addToFavorite(movie: MovieEntity){
        val params = AddToFavoriteUseCase.Params(movie)
        compositeDisposable.add(
            mAddToFavoriteUseCase(params)
                .subscribe({},{})
        )
    }

    fun removeFromFavorite(movieId: Int){
        val params = RemoveFromFavoriteUseCase.Params(movieId)
        compositeDisposable.add(
            mRemoveFromFavoriteUseCase(params)
                .subscribe({},{})
        )
    }

    fun isFavoriteMovie(movieId: Int){
        val params = CheckIsFavoriteMovieUseCase.Params(movieId)
        compositeDisposable.add(
            mCheckIsFavoriteMovieUseCase(params)
                .subscribe({
                   isFavorite.value = it
                },{})
        )
    }

}