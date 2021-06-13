package id.aibangstudio.moviedb.di

import androidx.room.Room
import id.aibangstudio.moviedb.core.scheduler.AppSchedulerProvider
import id.aibangstudio.moviedb.core.scheduler.SchedulerProvider
import id.aibangstudio.moviedb.data.db.AppDatabase
import id.aibangstudio.moviedb.data.remote.MovieApiInterceptor
import id.aibangstudio.moviedb.data.remote.createWebService
import id.aibangstudio.moviedb.data.remote.provideOkHttpClient
import id.aibangstudio.moviedb.data.remote.service.MovieService
import id.aibangstudio.moviedb.data.repository.MovieRepositoryImpl
import id.aibangstudio.moviedb.domain.repository.MovieRepository
import id.aibangstudio.moviedb.domain.usecase.*
import id.aibangstudio.moviedb.presentation.detail.MovieDetailViewModel
import id.aibangstudio.moviedb.presentation.main.fragments.favorite.FavoriteViewModel
import id.aibangstudio.moviedb.presentation.main.fragments.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MovieApiInterceptor() }
    single { provideOkHttpClient(get()) }
    single { createWebService<MovieService>(get()) }

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "movie-db").build()
    }

    single<SchedulerProvider> { AppSchedulerProvider()}

}

val dataModule = module {
    single { get<AppDatabase>().movieDao() }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}


val domainModule = module {
    single { GetPopularMoviesUseCase(get(), get()) }
    single { GetComingSoonUseCase(get(), get()) }
    single { GetMovieDetailUseCase(get(), get()) }
    single { AddToFavoriteUseCase(get(), get()) }
    single { RemoveFromFavoriteUseCase(get(), get()) }
    single { GetFavoriteMoviesUseCase(get(), get()) }
    single { CheckIsFavoriteMovieUseCase(get(), get()) }
}

val vmModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MovieDetailViewModel(get(),get(),get(),get()) }
    viewModel { FavoriteViewModel(get(), get()) }
}

val myAppModule = listOf(appModule, dataModule, domainModule, vmModule)