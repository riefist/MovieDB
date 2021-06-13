package id.aibangstudio.moviedb.presentation.main.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.aibangstudio.moviedb.databinding.FragmentHomeBinding
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.presentation.base.BaseFragment
import id.aibangstudio.moviedb.presentation.detail.MovieDetailActivity
import id.aibangstudio.moviedb.presentation.main.adapter.CarouselBannerAdapter
import id.aibangstudio.moviedb.presentation.main.adapter.ListMovieHorizontalAdapter
import id.aibangstudio.moviedb.utils.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), ListMovieHorizontalAdapter.OnMovieListener {

    private val vm: HomeViewModel by viewModel()
    private val mPopularMovieAdapter by lazy { ListMovieHorizontalAdapter() }
    private val mComingSoonMovieAdapter by lazy { ListMovieHorizontalAdapter() }
    private val mBannerAdapter by lazy { CarouselBannerAdapter(listOf()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPopularMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mPopularMovieAdapter
        }

        binding.rvComingSoonMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = mComingSoonMovieAdapter
        }

        mPopularMovieAdapter.setListener(this)
        mComingSoonMovieAdapter.setListener(this)

        binding.carouselView.adapter = mBannerAdapter
        binding.dotsIndicator.setViewPager2(binding.carouselView)

        vm.popularMovieResult.observe(viewLifecycleOwner, { state ->
        when(state) {
            is UiState.Loading -> {

            }
            is UiState.Success -> {
                mPopularMovieAdapter.setList(state.data.take(10))
                mBannerAdapter.setList(state.data.take(3))
            }
            is UiState.Error -> {

            }
        } })

        vm.comingSoonMovieResult.observe(viewLifecycleOwner, { state ->
            when(state) {
                is UiState.Loading -> {

                }
                is UiState.Success -> {
                    mComingSoonMovieAdapter.setList(state.data.take(10))
                }
                is UiState.Error -> {

                }
            } })

        vm.getPopularMovies()
        vm.getComingSoonMovies()
    }

    override fun onClicked(movie: MovieEntity) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        intent.putExtra("movie_id", movie.id)
        startActivity(intent)
    }

}