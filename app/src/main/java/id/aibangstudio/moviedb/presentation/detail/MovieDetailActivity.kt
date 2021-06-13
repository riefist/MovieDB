package id.aibangstudio.moviedb.presentation.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import id.aibangstudio.moviedb.R
import id.aibangstudio.moviedb.databinding.ActivityMovieDetailBinding
import id.aibangstudio.moviedb.domain.entity.MovieEntity
import id.aibangstudio.moviedb.presentation.base.BaseViewBindingActivity
import id.aibangstudio.moviedb.utils.AppLoadingDialog
import id.aibangstudio.moviedb.utils.UiState
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailActivity :
    BaseViewBindingActivity<ActivityMovieDetailBinding>(ActivityMovieDetailBinding::inflate) {

    private val vm: MovieDetailViewModel by viewModel()
    private lateinit var loadingDialog: AppLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Movie Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadingDialog = AppLoadingDialog(this)
        val movieId = intent.getIntExtra("movie_id", 0)

        vm.detailResult.observe(this, Observer { state ->
            when (state) {
                is UiState.Loading -> {
                    loadingDialog.show()
                }
                is UiState.Success -> {
                    loadingDialog.dismiss()
                    setDataToView(state.data)
                }
                is UiState.Error -> {
                    loadingDialog.dismiss()
                }
            }
        })

        vm.isFavorite.observe(this, {
            if (it){
                binding.btnAddToFavorite.text = "Remove Favorite"
                binding.btnAddToFavorite.setIconResource(R.drawable.ic_baseline_remove_24)
            } else {
                binding.btnAddToFavorite.text = "Add To Favorite"
                binding.btnAddToFavorite.setIconResource(R.drawable.ic_plus)
            }
        })

        vm.getMovieDetail(movieId)
        vm.isFavoriteMovie(movieId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setDataToView(movie: MovieEntity) = with(binding){
        Glide.with(this@MovieDetailActivity)
            .load(movie.posterUrl)
            .into(imgMovie)

        tvTitle.text = movie.title
        tvRating.text = "${movie.rating}"
        tvGenres.text = movie.genres.joinToString(separator = "・")
        tvOverview.text = movie.desc

        binding.btnAddToFavorite.setOnClickListener {
            if (vm.isFavorite.value == true){
                vm.removeFromFavorite(movie.id)
            } else {
                vm.addToFavorite(movie)
            }
        }

    }
}