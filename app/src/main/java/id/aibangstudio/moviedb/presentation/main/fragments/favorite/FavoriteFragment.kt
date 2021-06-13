package id.aibangstudio.moviedb.presentation.main.fragments.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import id.aibangstudio.moviedb.databinding.FragmentFavoriteBinding
import id.aibangstudio.moviedb.presentation.base.BaseFragment
import id.aibangstudio.moviedb.presentation.detail.MovieDetailActivity
import id.aibangstudio.moviedb.utils.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate), FavoriteAdapter.OnFavoriteListener {

    private val vm: FavoriteViewModel by viewModel()
    private val mFavoriteAdapter by lazy {
        FavoriteAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        mFavoriteAdapter.setListener(this)

        observeData()
        vm.getFavorites()
    }

    private fun initViews(){
        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mFavoriteAdapter
        }

        binding.inputSearchMovie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val result = vm.searchMovie(binding.inputSearchMovie.text.toString())
                mFavoriteAdapter.setList(result)

                // Only runs if there is a view that is currently focused
                requireActivity().currentFocus?.let { view ->
                    val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
            true
        }

        binding.inputSearchMovie.doAfterTextChanged {
            if (it.toString().isEmpty()){
                mFavoriteAdapter.setList(vm.favoriteList)
            }
        }
    }

    private fun observeData(){
        vm.favorites.observe(viewLifecycleOwner, { state ->
            when (state) {
                is UiState.Loading -> {

                }
                is UiState.Success -> {
                    mFavoriteAdapter.setList(state.data)
                }
                is UiState.Error -> {

                }
            }
        })
    }

    override fun onClicked(movieId: Int) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        intent.putExtra("movie_id", movieId)
        startActivity(intent)
    }

    override fun onBtnRemoveClicked(movieId: Int) {
        vm.removeFromFavorite(movieId)
    }
}