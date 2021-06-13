package id.aibangstudio.moviedb.presentation.main.fragments.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.aibangstudio.moviedb.databinding.ItemRowFavoritesBinding
import id.aibangstudio.moviedb.domain.entity.MovieEntity

class FavoriteAdapter(): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var mList = listOf<MovieEntity>()
    private var mListener : OnFavoriteListener? = null

    inner class FavoriteViewHolder(private val binding: ItemRowFavoritesBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(movie: MovieEntity) = with(binding){
            Glide.with(root.context)
                .load(movie.backdropUrl)
                .into(imgMovie)

            tvTitle.text = movie.title
            tvYear.text = movie.releaseDate.split("-")[0]
            tvGenres.text = movie.genres.joinToString(separator = ", ")

            root.setOnClickListener {
                mListener?.onClicked(movie.id)
            }

            btnRemoveFavorite.setOnClickListener {
                mListener?.onBtnRemoveClicked(movie.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemRowFavoritesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(data: List<MovieEntity>){
        mList = data
        notifyDataSetChanged()
    }

    fun setListener(listener: OnFavoriteListener){
        mListener = listener
    }

    interface OnFavoriteListener {
        fun onClicked(movieId: Int)
        fun onBtnRemoveClicked(movieId: Int)
    }
}