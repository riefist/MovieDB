package id.aibangstudio.moviedb.presentation.main.fragments.home

import androidx.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.e
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.domain.usecase.GetComingSoonUseCase
import id.aibangstudio.moviedb.domain.usecase.GetPopularMoviesUseCase
import id.aibangstudio.moviedb.presentation.base.BaseViewModel
import id.aibangstudio.moviedb.utils.UiState

class HomeViewModel(
    private val mGetPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val mGetComingSoonUseCase: GetComingSoonUseCase
) : BaseViewModel() {

    val popularMovieResult = MutableLiveData<UiState<List<MovieEntity>>>()
    val comingSoonMovieResult = MutableLiveData<UiState<List<MovieEntity>>>()

    fun getPopularMovies(){
        compositeDisposable.add(
            mGetPopularMoviesUseCase()
                .subscribe({
                    popularMovieResult.value = UiState.Success(it)
                },{
                    e { it.localizedMessage }
                    popularMovieResult.value = UiState.Error(it)
                })
        )
    }

    fun getComingSoonMovies(){
        compositeDisposable.add(
            mGetComingSoonUseCase()
                .subscribe({
                    comingSoonMovieResult.value = UiState.Success(it)
                },{
                    e { it.localizedMessage }
                    comingSoonMovieResult.value = UiState.Error(it)
                })
        )
    }

}