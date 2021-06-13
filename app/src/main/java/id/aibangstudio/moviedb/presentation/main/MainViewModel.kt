package id.aibangstudio.moviedb.presentation.main

import androidx.lifecycle.MutableLiveData
import id.aibangstudio.moviedb.presentation.base.BaseViewModel
import id.aibangstudio.moviedb.utils.UiState
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.usecase.GetPopularMoviesUseCase

class MainViewModel(
    private val mGetPopularMoviesUseCase: GetPopularMoviesUseCase,
) : BaseViewModel() {

    val popularMovieResult = MutableLiveData<UiState<List<MovieEntity>>>()

    fun getPopularMovies(){
        compositeDisposable.add(
            mGetPopularMoviesUseCase()
                .subscribe({
                    popularMovieResult.value = UiState.Success(it)
                },{
                    popularMovieResult.value = UiState.Error(it)
                })
        )
    }


}